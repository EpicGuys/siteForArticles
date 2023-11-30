package com.epicGuys.app.articles.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping(path = "/epic-guys")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private Validator validator;
	
	@GetMapping("/users")
	@ResponseStatus(HttpStatus.OK)
	public Response<List<User>> getAllUsers() throws NotFoundException{
		List<User> users = userService.getAllUsers();
		if(users.isEmpty()) {
			throw new NotFoundException("Data base is empty");
		}
		return new Response<List<User>>(HttpStatus.OK, users);
	}
	
	@GetMapping("/users/{id}")
	@ResponseStatus(HttpStatus.OK) 
	public Response<User> getUser(@PathVariable("id") String id) throws NotFoundException, ValidationException{
		if(!validator.isIdValid(id)) {
			throw new ValidationException("Id is not valid");
		}
		Optional<User> user = userService.getUser(Long.valueOf(id));
		if(user.isEmpty()) {
			throw new NotFoundException("User does not exist");
		}
		return new Response<User>(HttpStatus.OK, user.get());
	}
	
	@PostMapping("/users/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Response<User> addUser(@RequestBody User user) throws ValidationException{
		if(!validator.isUserValid(user)) {
			throw new ValidationException("Fields are not valid");
		}
		return new Response<User>(HttpStatus.CREATED, userService.saveUser(user));
	}
	
	@DeleteMapping("/users/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Response<User> deleteUser(@PathVariable("id") String id) throws NotFoundException, ValidationException{
		if(!validator.isIdValid(id)) {
			throw new ValidationException("Id is not valid");
		}
		Optional<User> user = userService.getUser(Long.valueOf(id));
		if(user.isEmpty()) {
			throw new NotFoundException("User does not exist");
		}
		userService.deleteUser(Long.valueOf(id));
		return new Response<User>(HttpStatus.OK, user.get());
	}
	
	@PostMapping("/users/{userId}/articles")
	@ResponseStatus(HttpStatus.CREATED)
	public Response<Article> addArticle(@PathVariable("userId") String userId, @RequestBody Article article) throws NotFoundException, ValidationException{
		if(!validator.isIdValid(userId) || !validator.isArticleValid(article)) {
			throw new ValidationException("Validation error");
		}
		Optional<User> user = userService.getUser(Long.valueOf(userId));
		if(user.isEmpty()) {
			throw new NotFoundException("User does not exist");
		}
		article.setUser(user.get());
		articleService.saveArticle(article);
		return new Response<Article>(HttpStatus.CREATED, article);
	}
	
	@GetMapping("/users/{userId}/articles")
	@ResponseStatus(HttpStatus.OK)
	public Response<List<Article>> getAllArticles(@PathVariable("userId") String userId) throws NotFoundException, ValidationException{
		if(!validator.isIdValid(userId)) {
			throw new ValidationException("Validation error");
		}
		Optional<User> user = userService.getUser(Long.valueOf(userId));
		if(user.isEmpty()) {
			throw new NotFoundException("User does not exist");
		}
		List<Article> articles = articleService.getAllArticles(user.get());
		return new Response<List<Article>>(HttpStatus.OK, articles);
	}

	@DeleteMapping("/users/articles/{articleId}")
	@ResponseStatus(HttpStatus.OK)
	public Response<List<Article>> deleteArticle(@PathVariable("articleId") String articleId) throws NotFoundException, ValidationException{
		if(!validator.isIdValid(articleId)) {
			throw new ValidationException("Id is not valid");
		}
		Optional<Article> article = articleService.getArticle(Long.valueOf(articleId));
		if(article.isEmpty()) {
			throw new NotFoundException("Article does not exist");
		}
		articleService.deleteArticle(Long.valueOf(articleId));
		return new Response<List<Article>>(HttpStatus.OK, null);
	}
}
