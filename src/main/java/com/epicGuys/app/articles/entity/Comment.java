package com.epicGuys.app.articles.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "text")
	private StringBuffer text;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Article article;
	
	@Embedded
	private Person person;
}
