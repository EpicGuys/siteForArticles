package com.epicGuys.app.articles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.epicGuys.app.articles.entity.User;
import com.epicGuys.app.articles.exception.NotFoundException;
import com.epicGuys.app.articles.jpa.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
		
	public String getNicknameOfCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
	
	public Long getUserIdByNickname(String nickname) throws NotFoundException{
		Optional<User> user = userRepository.getUserByNickname(nickname);
		if(user.isEmpty()) {
			throw new NotFoundException("There is no authenticated user");
		}
		return user.get().getId();
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> getUser(Long id) {
		return userRepository.findById(id);
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
