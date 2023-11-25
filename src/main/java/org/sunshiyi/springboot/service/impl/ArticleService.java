package org.sunshiyi.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sunshiyi.springboot.mapper.ArticleMapper;
import org.sunshiyi.springboot.pojo.Article;
import org.sunshiyi.springboot.service.IArticleService;
import org.sunshiyi.springboot.utils.ThreadLocalUtil;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ArticleService implements IArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void addArticle(Article article) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        article.setCreateUser(id);
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.addArticle(article);
    }
}
