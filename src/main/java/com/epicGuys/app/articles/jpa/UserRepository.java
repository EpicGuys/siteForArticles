package com.epicGuys.app.articles.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epicGuys.app.articles.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
