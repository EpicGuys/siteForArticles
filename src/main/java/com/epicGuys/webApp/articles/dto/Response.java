package com.epicGuys.webApp.articles.dto;

import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response<T> {
	private HttpStatus httpStatus;
	private T data;
	
	public Response(HttpStatus httpStatus, T data) {
		this.httpStatus = httpStatus;
		this.data = data;
	}
}
