package com.epicGuys.webApp.articles.validator;

import org.springframework.stereotype.Service;

import com.epicGuys.webApp.articles.entity.Article;
import com.epicGuys.webApp.articles.entity.User;

@Service
public class Validator {
	public Boolean isUserValid(User user) {
		return true;
	}
	
	public Boolean isArticleValid(Article article) {
		return true;
	}
	
	public Boolean isIdValid(String id) {
		return true;
	}
}
