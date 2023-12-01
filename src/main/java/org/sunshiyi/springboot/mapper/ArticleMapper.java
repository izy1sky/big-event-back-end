package org.sunshiyi.springboot.mapper;

import org.apache.ibatis.annotations.*;
import org.sunshiyi.springboot.pojo.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time) VALUES " +
            "(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
    void addArticle(Article article);

    List<Article> findByConditions(Integer id, Integer categoryId, String state);

    @Update("update article set title = #{title}, content = #{content}, cover_img = #{coverImg}, state = #{state}, category_id = #{categoryId}, create_user = #{createUser}, update_time = #{updateTime} where id = #{id}")
    void updateArticle(Article article);

    @Select("select * from article where id = #{id}")
    Article getDetail(Integer id);

    @Delete("delete from article where id = #{id}")
    void deleteArticle(Integer id);
}
