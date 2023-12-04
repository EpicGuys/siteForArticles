package com.epicGuys.app.articles.validator;

import org.springframework.stereotype.Service;

import com.epicGuys.app.articles.entity.Article;
import com.epicGuys.app.articles.entity.User;

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
	
	public Boolean isSubjectValid(String subject) {
		return true;
	}
}
