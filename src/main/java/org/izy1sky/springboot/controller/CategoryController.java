package org.izy1sky.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.izy1sky.springboot.pojo.Category;
import org.izy1sky.springboot.pojo.Result;
import org.izy1sky.springboot.service.ICategoryService;

import java.util.List;

@RequestMapping("/category")
@RestController
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping
    public Result<List<Category>> getCategories() {
        List<Category> categories = categoryService.getCategories();
        return Result.success(categories);
    }

    @PostMapping
    public Result<Object> addCategory(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.addCategory(category);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result<Category> getDetail(@RequestParam Integer id) {
        Category category = categoryService.getDetail(id);
        return Result.success(category);
    }

    @PutMapping
    public Result<Object> updateCategory(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.updateCategory(category);
        return Result.success();
    }
}
