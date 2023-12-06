package com.epicGuys.app.articles.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.epicGuys.app.articles.dto.Response;
import com.epicGuys.app.articles.entity.Article;
import com.epicGuys.app.articles.exception.NotFoundException;
import com.epicGuys.app.articles.exception.ValidationException;

@Controller
public class MvcController {
	@RequestMapping("/sport")
    public String getSportPage() {
        return "sport";
    }
	
	@RequestMapping("/it")
    public String getItPage() {
        return "it";
    }
	
	@RequestMapping("/science")
    public String getSciencePage() {
        return "science";
    }
	
	@RequestMapping("/travel")
    public String getTravelPage() {
        return "travel";
    }
	
	@RequestMapping("/epic-guys/articles/writer/viewAll")
    public String getWriterArticlesPage() {
        return "userarticles";
    }
	
	@RequestMapping("/epic-guys/articles/writer/delete")
	public String deleleArticles() {
		return "deleteArticles";
	}
	
	@RequestMapping("/epic-guys/articles/writer/delete/article/{id}")
	public String deleleArticle(@PathVariable String id, Model model) {
		model.addAttribute("articleId", id);
		return "delete";
	}
	
	@RequestMapping("/epic-guys/articles/view/article/{id}")
	public String viewArticle(@PathVariable String id, Model model) {
		model.addAttribute("articleId", id);
		return "view";
	}
	
	@RequestMapping("/epic-guys/articles/writer/write")
	public String writeArticle() {
		return "write";
	}
}
