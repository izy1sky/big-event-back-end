package org.izy1sky.springboot.controller;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.izy1sky.springboot.pojo.Result;
import org.izy1sky.springboot.pojo.User;
import org.izy1sky.springboot.pojo.UserPasswordDTO;
import org.izy1sky.springboot.service.IUserService;
import org.izy1sky.springboot.utils.JwtUtil;
import org.izy1sky.springboot.utils.Md5Util;
import org.izy1sky.springboot.utils.ThreadLocalUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private StringRedisTemplate template;

    @PostMapping("/register")
    public Result<Object> register(@Pattern(regexp = "^\\w{5,12}$") String username, @Pattern(regexp = "^\\w{5,12}$") String password) throws Exception {
        // select user
        User user = userService.findByUserName(username);

        if (user == null) {
            userService.register(username, password);
            return Result.success();
        } else {
            throw new Exception("用户名已占用！");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\w{5,12}$") String username, @Pattern(regexp = "^\\w{5,12}$") String password) throws Exception {
        // 查询用户
        User loginUser = userService.findByUserName(username);
        if (loginUser == null) {
            throw new Exception("用户名错误！");
        }
        if (Md5Util.checkPassword(password, loginUser.getPassword())) {
            // 生成token 载荷记录用户的id和username
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", username);
            String token = JwtUtil.genToken(claims);
            ValueOperations<String, String> operations = template.opsForValue();
            operations.set(token, token, 1, TimeUnit.HOURS);
            return Result.success(token);
        } else {
            throw new Exception("密码错误！");
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
    public Result<Object> update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result<Object> updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }
    @PatchMapping("/updatePwd")
    public Result<Object> updatePwd(@RequestBody @Validated UserPasswordDTO userPasswordDTO, @RequestHeader("Authorization") String token) {
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
        ValueOperations<String, String> operations = template.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }
}
