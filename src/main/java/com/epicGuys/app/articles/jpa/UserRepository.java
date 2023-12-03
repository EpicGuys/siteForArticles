package com.epicGuys.app.articles.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.epicGuys.app.articles.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> getUserByNickname(String nickname);
}
