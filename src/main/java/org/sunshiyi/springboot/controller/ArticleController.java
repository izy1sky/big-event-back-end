package org.sunshiyi.springboot.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.sunshiyi.springboot.pojo.Article;
import org.sunshiyi.springboot.pojo.Result;
import org.sunshiyi.springboot.service.IArticleService;
import org.sunshiyi.springboot.utils.JwtUtil;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;
    @PostMapping
    public Result addArticle(@RequestBody @Validated(Article.Add.class) Article article) {
        articleService.addArticle(article);
        return Result.success();
    }
}
