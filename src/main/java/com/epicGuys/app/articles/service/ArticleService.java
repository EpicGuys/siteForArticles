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
	
	public Optional<Article> editArticle(Long id, Article article) {
		Optional<Article> optionalArticle = articleRepository.findById(id);
		if(article.getSubject() != null) {
			optionalArticle.get().setSubject(article.getSubject());
		}
		if(article.getTitle() != null) {
			optionalArticle.get().setTitle(article.getTitle());
		}
		if(article.getText() != null) {
			optionalArticle.get().setText(article.getText());
		}
		articleRepository.save(optionalArticle.get());
		return optionalArticle;
	}
	
	public List<Article> findArticleBySubjectAndTitle(String subject, String title) {
		return articleRepository.findBySubjectAndTitleContaining(subject, title);
	}
	
	public List<Article> findArticleByTitle(String titlePart) {
		return articleRepository.findByTitleContaining(titlePart);
	}
	
	public List<Article> getAllArticles(User user) {
		Article article = new Article();
		article.setUser(user);
		Example<Article> articleExample = Example.of(article);
		return articleRepository.findAll(articleExample);
	}
	
	public List<Article> getArticlesBySubject(String subject) {
		Article article = new Article();
		article.setSubject(subject);
		Example<Article> articleExample = Example.of(article);
		return articleRepository.findAll(articleExample);
	}
	
	public List<Article> getAllArticles() {
		return articleRepository.findAll();
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
