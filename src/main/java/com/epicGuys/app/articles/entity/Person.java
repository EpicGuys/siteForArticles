package com.epicGuys.app.articles.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
@Embeddable
public class Person {
	private String nickname;
}
