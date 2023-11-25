package org.sunshiyi.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sunshiyi.springboot.mapper.CategoryMapper;
import org.sunshiyi.springboot.pojo.Category;
import org.sunshiyi.springboot.service.ICategoryService;
import org.sunshiyi.springboot.utils.ThreadLocalUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> getCategories() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        return categoryMapper.getCategories(id);
    }

    @Override
    public void addCategory(Category category) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        category.setCreateUser(id);
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateTime(LocalDateTime.now());
        categoryMapper.addCategory(category);
    }

    @Override
    public Category getDetail(Integer id) {
        return categoryMapper.getDetail(id);
    }

    @Override
    public void updateCategory(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.updateCategory(category);
    }
}
