package com.epicGuys.app.articles.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.epicGuys.app.articles.entity.User;
import com.epicGuys.app.articles.jpa.UserRepository;

@Repository
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.getUserByNickname(username);
		if(user.isEmpty()) {
			throw new UsernameNotFoundException("Could not find user");
		}
		return new MyUserDetails(user.get());
	}
}
