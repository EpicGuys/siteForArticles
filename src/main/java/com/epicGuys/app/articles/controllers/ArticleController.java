package com.epicGuys.app.articles.controllers;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.epicGuys.app.articles.dto.Response;
import com.epicGuys.app.articles.entity.Article;
import com.epicGuys.app.articles.entity.User;
import com.epicGuys.app.articles.exception.NotFoundException;
import com.epicGuys.app.articles.exception.ValidationException;
import com.epicGuys.app.articles.service.ArticleService;
import com.epicGuys.app.articles.service.UserService;
import com.epicGuys.app.articles.validator.Validator;

@RestController
@RequestMapping(path = "/epic-guys/articles")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private Validator validator;
	
	@PostMapping("/writer/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Response<Article> addArticle(@RequestBody Article article) throws NotFoundException, ValidationException{
		if(!validator.isArticleValid(article)) {
			throw new ValidationException("Validation error");
		}
		Optional<User> user = userService.getUser(Long.valueOf(userService.getUserIdByNickname(userService.getNicknameOfCurrentUser())));
		if(user.isEmpty()) {
			throw new NotFoundException("User does not exist");
		}
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		article.setUser(user.get());
		article.setDate(dateFormat.format(date));
		articleService.saveArticle(article);
		return new Response<Article>(HttpStatus.CREATED, article);
	}
	
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public Response<List<Article>> getAllArticles() throws NotFoundException{
		List<Article> articles = articleService.getAllArticles();
		if(articles.isEmpty()) {
			throw new NotFoundException("Articles do not exist");
		}
		Collections.reverse(articles);
		return new Response<List<Article>>(HttpStatus.OK, articles);
	}
	
	@GetMapping("/search/{subject}/{title}")
	@ResponseStatus(HttpStatus.OK)
	public Response<List<Article>> getArticlesBySubjectAndTitle(@PathVariable("subject") String subject, 
															@PathVariable("title") String title) throws NotFoundException, ValidationException{
		if(!validator.isSubjectValid(subject)) {
			throw new ValidationException("Validation error");
		}
		List<Article> articles = articleService.findArticleBySubjectAndTitle(subject.toLowerCase(), title.toLowerCase());
		if(articles.isEmpty()) {
			throw new NotFoundException("Articles do not exist");
		}
		return new Response<List<Article>>(HttpStatus.OK, articles);
	}
	
	@GetMapping("/subject/{subject}")
	@ResponseStatus(HttpStatus.OK)
	public Response<List<Article>> getArticlesBySubject(@PathVariable("subject") String subject) throws NotFoundException, ValidationException{
		if(!validator.isSubjectValid(subject)) {
			throw new ValidationException("Validation error");
		}
		List<Article> articles = articleService.getArticlesBySubject(subject.toLowerCase());
		if(articles.isEmpty()) {
			throw new NotFoundException("Articles do not exist");
		}
		return new Response<List<Article>>(HttpStatus.OK, articles);
	}
	
	@GetMapping("/title/{title}")
	@ResponseStatus(HttpStatus.OK)
	public Response<List<Article>> getArticlesByTitlePart(@PathVariable("title") String title) throws NotFoundException, ValidationException{
		if(title.length() == 0) {
			throw new ValidationException("Validation error");
		}
		List<Article> articles = articleService.findArticleByTitle(title);
		if(articles.isEmpty()) {
			throw new NotFoundException("Articles do not exist");
		}
		return new Response<List<Article>>(HttpStatus.OK, articles);
	}
	
	
	@GetMapping("/writer/view")
	@ResponseStatus(HttpStatus.OK)
	public Response<List<Article>> getAllUserArticles() throws NotFoundException{
		Optional<User> user = userService.getUser(Long.valueOf(userService.getUserIdByNickname(userService.getNicknameOfCurrentUser())));
		if(user.isEmpty()) {
			throw new NotFoundException("User does not exist");
		}
		List<Article> articles = articleService.getAllArticles(user.get());
		Collections.reverse(articles);
		return new Response<List<Article>>(HttpStatus.OK, articles);
	}

	@DeleteMapping("/writer/delete/{articleId}")
	@ResponseStatus(HttpStatus.OK)
	public Response<Article> deleteArticle(@PathVariable("articleId") String articleId) throws NotFoundException, ValidationException{
		if(!validator.isIdValid(articleId)) {
			throw new ValidationException("Id is not valid");
		}
		Optional<Article> article = articleService.getArticle(Long.valueOf(articleId));
		if(article.isEmpty()) {
			throw new NotFoundException("Article does not exist");
		}
		articleService.deleteArticle(Long.valueOf(articleId));
		return new Response<Article>(HttpStatus.OK, article.get());
	}
}


