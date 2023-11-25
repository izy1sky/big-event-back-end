package org.sunshiyi.springboot.controller;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.sunshiyi.springboot.pojo.Result;
import org.sunshiyi.springboot.pojo.User;
import org.sunshiyi.springboot.pojo.UserPasswordDTO;
import org.sunshiyi.springboot.service.IUserService;
import org.sunshiyi.springboot.utils.JwtUtil;
import org.sunshiyi.springboot.utils.Md5Util;
import org.sunshiyi.springboot.utils.ThreadLocalUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\w{5,12}$") String username,@Pattern(regexp = "^\\w{5,12}$") String password) {
        // select user
        User user = userService.findByUserName(username);

        if (user == null) {
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名已占用！");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\w{5,12}$") String username, @Pattern(regexp = "^\\w{5,12}$") String password) {
        // 查询用户
        User loginUser = userService.findByUserName(username);
        if (loginUser == null) {
            return Result.error("用户名错误！");
        }
        if (Md5Util.checkPassword(password, loginUser.getPassword())) {
            // 生成token 载荷记录用户的id和username
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", username);
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        } else {
            return Result.error("密码错误！");
        }
    }

    @GetMapping("/userInfo")
    public Result<User> getUserInfo() {
         Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody @Validated UserPasswordDTO userPasswordDTO) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        String username = (String) claims.get("username");
        // 旧密码输入不正确
        User user = userService.findByUserName(username);
        if (!Md5Util.checkPassword(userPasswordDTO.getOldPwd(), user.getPassword())) {
            return Result.error("旧密码输入不正确");
        }
        // 两次密码输入不同
        if (!userPasswordDTO.getNewPwd().equals(userPasswordDTO.getRePwd())) {
            return Result.error("两次密码输入不同！");
        }
        String md5String = Md5Util.getMD5String(userPasswordDTO.getNewPwd());
        userService.updatePwd(id, md5String);
        return Result.success();
    }
}
