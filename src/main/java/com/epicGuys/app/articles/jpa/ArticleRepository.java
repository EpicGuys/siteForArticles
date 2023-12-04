package com.epicGuys.app.articles.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.epicGuys.app.articles.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	List<Article> findByTitleContaining(String part);
}
