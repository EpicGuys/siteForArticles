package com.epicGuys.app.articles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epicGuys.app.articles.service.ArticleService;
import com.epicGuys.app.articles.validator.Validator;

@RestController
@RequestMapping(path = "/epic-guys")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private Validator validator;
}