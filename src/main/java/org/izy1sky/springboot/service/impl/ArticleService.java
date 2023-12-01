package org.izy1sky.springboot.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.izy1sky.springboot.mapper.ArticleMapper;
import org.izy1sky.springboot.pojo.Article;
import org.izy1sky.springboot.pojo.ArticlePageDTO;
import org.izy1sky.springboot.pojo.PageBean;
import org.izy1sky.springboot.service.IArticleService;
import org.izy1sky.springboot.utils.ThreadLocalUtil;

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

    @Override
    public PageBean<Article> articlePageQuery(ArticlePageDTO articlePageDTO) {
        PageBean<Article> pageBean = new PageBean<>();
        PageHelper.startPage(articlePageDTO.getPageNum(), articlePageDTO.getPageSize());
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
//        List<Article> articleList = articleMapper.findByConditions(id, articlePageDTO.getCategoryId(), articlePageDTO.getState());
        // TODO cannot cast List to Page
        Page<Article> articles = (Page<Article>) articleMapper.findByConditions(id, articlePageDTO.getCategoryId(), articlePageDTO.getState());
        pageBean.setTotal(articles.getTotal());
        pageBean.setItems(articles.getResult());
        return pageBean;
    }

    @Override
    public void updateArticle(Article article) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        article.setCreateUser(id);
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.updateArticle(article);
    }

    @Override
    public Article getDetail(Integer id) {
        return articleMapper.getDetail(id);
    }

    @Override
    public void deleteArticle(Integer id) {
        articleMapper.deleteArticle(id);
    }
}
