package org.izy1sky.springboot.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.izy1sky.springboot.pojo.Article;
import org.izy1sky.springboot.pojo.ArticlePageDTO;
import org.izy1sky.springboot.pojo.PageBean;
import org.izy1sky.springboot.pojo.Result;
import org.izy1sky.springboot.service.IArticleService;

@RestController
@Validated
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;
    @PostMapping
    public Result<Object> addArticle(@RequestBody @Validated(Article.Add.class) Article article) {
        articleService.addArticle(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> articlePageQuery(@Validated ArticlePageDTO articlePageDTO) {
        PageBean<Article> pageBean = articleService.articlePageQuery(articlePageDTO);
        return Result.success(pageBean);
    }

    @PutMapping
    public Result<Object> updateArticle(@RequestBody @Validated(Article.Update.class) Article article) {
        articleService.updateArticle(article);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result<Article> getDetail(@RequestParam @NotNull Integer id) throws Exception {
        Article detail = articleService.getDetail(id);
        if (detail == null) {
            throw new Exception("获取文章失败！文章不存在！");
        } else {
            return Result.success(detail);
        }
    }

    @DeleteMapping
    public Result<Object> deleteArticle(@RequestParam @NotNull Integer id) {
        articleService.deleteArticle(id);
        return Result.success();
    }
}
