package com.epicGuys.app.articles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
