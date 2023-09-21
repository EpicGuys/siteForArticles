package com.epicGuys.app.articles.controllers;

import com.epicGuys.app.articles.entity.Role;
import com.epicGuys.app.articles.entity.User;
import com.epicGuys.app.articles.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(@RequestParam String username, @RequestParam String password){
        if(userRepository.getUserByUsername(username) != null){
            return "User with this name already exists";
        }
        User user = new User();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEnabled(true);
        user.setRoles(Collections.singleton(Role.ROLE_ADMIN));
        userRepository.save(user);
        return "registration successful";
    }
}
