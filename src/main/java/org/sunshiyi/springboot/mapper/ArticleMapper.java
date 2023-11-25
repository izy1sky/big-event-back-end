package org.sunshiyi.springboot.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.sunshiyi.springboot.pojo.Article;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time) VALUES " +
            "(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
    void addArticle(Article article);

    ArrayList<Article> findByConditions(Integer id, Integer categoryId, String state);
}
