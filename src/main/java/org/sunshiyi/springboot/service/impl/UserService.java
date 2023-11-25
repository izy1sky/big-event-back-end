package org.sunshiyi.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sunshiyi.springboot.mapper.UserMapper;
import org.sunshiyi.springboot.pojo.Result;
import org.sunshiyi.springboot.pojo.User;
import org.sunshiyi.springboot.service.IUserService;
import org.sunshiyi.springboot.utils.Md5Util;
import org.sunshiyi.springboot.utils.ThreadLocalUtil;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void register(String username, String password) {
        // 加密密码
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username, md5String);
    }

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        userMapper.updateAvatar(id, avatarUrl);
    }

    @Override
    public void updatePwd(Integer id, String password) {
        userMapper.updatePwd(id, password);
    }
}
