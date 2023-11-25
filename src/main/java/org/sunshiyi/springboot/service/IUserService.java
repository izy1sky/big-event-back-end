package org.sunshiyi.springboot.service;

import org.sunshiyi.springboot.pojo.User;

import java.util.Map;

public interface IUserService {
    void register(String username, String password);

    User findByUserName(String username);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(Integer id, String md5String);
}
