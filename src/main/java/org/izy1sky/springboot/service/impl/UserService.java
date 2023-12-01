package org.izy1sky.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.izy1sky.springboot.mapper.UserMapper;
import org.izy1sky.springboot.pojo.User;
import org.izy1sky.springboot.service.IUserService;
import org.izy1sky.springboot.utils.Md5Util;
import org.izy1sky.springboot.utils.ThreadLocalUtil;

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
