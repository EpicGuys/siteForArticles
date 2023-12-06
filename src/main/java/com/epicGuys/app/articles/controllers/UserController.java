package com.epicGuys.app.articles.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.epicGuys.app.articles.dto.Response;
import com.epicGuys.app.articles.entity.Role;
import com.epicGuys.app.articles.entity.User;
import com.epicGuys.app.articles.exception.NotFoundException;
import com.epicGuys.app.articles.exception.ValidationException;
import com.epicGuys.app.articles.service.UserService;
import com.epicGuys.app.articles.validator.Validator;

@RestController
@RequestMapping(path = "/epic-guys/users")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private Validator validator;
	
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public Response<List<User>> getAllUsers() throws NotFoundException{
		List<User> users = userService.getAllUsers();
		if(users.isEmpty()) {
			throw new NotFoundException("Data base is empty");
		}
		return new Response<List<User>>(HttpStatus.OK, users);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK) 
	public Response<User> getUser(@PathVariable("id") String id) throws NotFoundException, ValidationException{
		if(!validator.isIdValid(id)) {
			throw new ValidationException("Id is not valid");
		}
		Optional<User> user = userService.getUser(Long.valueOf(id));
		if(user.isEmpty()) {
			throw new NotFoundException("User does not exist");
		}
		return new Response<User>(HttpStatus.OK, user.get());
	}
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Response<User> addUser(@RequestBody User user) throws ValidationException{
		if(!validator.isUserValid(user)) {
			throw new ValidationException("Fields are not valid");
		}
		User newUser = new User();
		newUser.setNickname(user.getNickname());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setEnabled(true);
		newUser.setRoles(Set.of(Role.ROLE_WRITER));
		return new Response<User>(HttpStatus.CREATED, userService.saveUser(newUser));
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Response<User> deleteUser(@PathVariable("id") String id) throws NotFoundException, ValidationException{
		if(!validator.isIdValid(id)) {
			throw new ValidationException("Id is not valid");
		}
		Optional<User> user = userService.getUser(Long.valueOf(id));
		if(user.isEmpty()) {
			throw new NotFoundException("User does not exist");
		}
		userService.deleteUser(Long.valueOf(id));
		return new Response<User>(HttpStatus.OK, user.get());
	}
}
