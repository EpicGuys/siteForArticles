package com.epicGuys.webApp.articles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.epicGuys.webApp.articles.dto.Response;

@RestControllerAdvice
public class AdvisorController {
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<String> validationExceptionHandler(ValidationException exception) {
		return (new Response<String>(HttpStatus.BAD_REQUEST, exception.getMessage()));
	}
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Response<String> notFoundExceptionHandler(NotFoundException exception) {
		return (new Response<String>(HttpStatus.NOT_FOUND, exception.getMessage()));
	}
}
