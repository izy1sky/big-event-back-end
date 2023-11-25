package org.sunshiyi.springboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.sunshiyi.springboot.pojo.User;

@Mapper
public interface UserMapper {
    @Insert("insert into user(username, password, create_time, update_time) " +
            "values(#{username}, #{password}, now(), now())")
    void add(String username, String password);

    @Select("select * from user where username = #{username}")
    User findByUserName(String username);

    @Update("update user set nickname = #{nickname}, email = #{email}, update_time = #{updateTime} where id = #{id}")
    void update(User user);

    @Update("update user set user_pic = #{avatarUrl}, update_time = now() where id = #{id}")
    void updateAvatar(Integer id, String avatarUrl);

    @Update("update user set password = #{password}, update_time = now() where id = #{id}")
    void updatePwd(Integer id, String password);
}
