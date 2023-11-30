package com.epicGuys.app.articles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.epicGuys.app.articles.entity.Article;
import com.epicGuys.app.articles.entity.User;
import com.epicGuys.app.articles.jpa.ArticleRepository;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	public List<Article> getAllArticles(User user) {
		Article article = new Article();
		article.setUser(user);
		Example<Article> articleExample = Example.of(article);
		return articleRepository.findAll(articleExample);
	}
	
	public Article saveArticle(Article article) {
		return articleRepository.save(article);
	}
	
	public Optional<Article> getArticle(Long id) {
		return articleRepository.findById(id);
	}
	
	public void deleteArticle(Long id) {
		articleRepository.deleteById(id);
	}
}