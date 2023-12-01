package org.izy1sky.springboot.service;

import org.izy1sky.springboot.pojo.User;

public interface IUserService {
    void register(String username, String password);

    User findByUserName(String username);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(Integer id, String md5String);
}
