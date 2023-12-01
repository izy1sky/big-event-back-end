package org.izy1sky.springboot.service;

import org.izy1sky.springboot.pojo.Article;
import org.izy1sky.springboot.pojo.ArticlePageDTO;
import org.izy1sky.springboot.pojo.PageBean;

public interface IArticleService {
    void addArticle(Article article);

    PageBean<Article> articlePageQuery(ArticlePageDTO articlePageDTO);

    void updateArticle(Article article);

    Article getDetail(Integer id);

    void deleteArticle(Integer id);
}
