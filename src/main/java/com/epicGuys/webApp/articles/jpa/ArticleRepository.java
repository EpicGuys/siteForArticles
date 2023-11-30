package com.epicGuys.webApp.articles.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.epicGuys.webApp.articles.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}