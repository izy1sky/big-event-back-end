package org.sunshiyi.springboot.service;

import org.sunshiyi.springboot.pojo.Article;
import org.sunshiyi.springboot.pojo.ArticlePageDTO;
import org.sunshiyi.springboot.pojo.PageBean;

public interface IArticleService {
    void addArticle(Article article);

    PageBean<Article> articlePageQuery(ArticlePageDTO articlePageDTO);

    void updateArticle(Article article);

    Article getDetail(Integer id);

    void deleteArticle(Integer id);
}
