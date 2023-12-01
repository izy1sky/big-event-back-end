package org.izy1sky.springboot.service;

import org.izy1sky.springboot.pojo.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getCategories();

    void addCategory(Category category);

    Category getDetail(Integer id);

    void updateCategory(Category category);
}
