package org.izy1sky.springboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.izy1sky.springboot.pojo.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("select * from category where create_user = #{id}")
    List<Category> getCategories(Integer id);

    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time) VALUES " +
            "(#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
    void addCategory(Category category);

    @Select("select * from category where id = #{id}")
    Category getDetail(Integer id);

    @Update("update category set category_name = #{categoryName}, category_alias = #{categoryAlias}, update_time = #{updateTime} where id = #{id}")
    void updateCategory(Category category);
}
