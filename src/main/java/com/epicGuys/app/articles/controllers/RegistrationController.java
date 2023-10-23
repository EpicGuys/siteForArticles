package com.epicGuys.app.articles.controllers;

import com.epicGuys.app.articles.entity.Role;
import com.epicGuys.app.articles.entity.User;
import com.epicGuys.app.articles.repository.UserRepository;
import com.epicGuys.app.articles.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping(path = "api/public")
public class RegistrationController {
        private final AuthenticationManager authenticationManager;
        private final JwtUtil jwtUtil;

        public RegistrationController(AuthenticationManager authenticationManager,
                                      JwtUtil jwtUtil) {
            this.authenticationManager = authenticationManager;
            this.jwtUtil = jwtUtil;
        }

        @PostMapping("login")
        public ResponseEntity login(@RequestBody User request) {
            try {
                Authentication authenticate = authenticationManager
                        .authenticate(
                                new UsernamePasswordAuthenticationToken(
                                        request.getUsername(), request.getPassword()
                                )
                        );

                User user = (User) authenticate.getPrincipal();

                return new ResponseEntity(HttpStatus.OK);

            } catch (BadCredentialsException ex) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
    }


//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/registration")
//    public String registration(@RequestParam String username, @RequestParam String password){
//        if(userRepository.getUserByUsername(username) != null){
//            return "User with this name already exists";
//        }
//        User user = new User();
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        user.setUsername(username);
//        user.setPassword(bCryptPasswordEncoder.encode(password));
//        user.setEnabled(true);
//        user.setRoles(Collections.singleton(Role.ROLE_ADMIN));
//        userRepository.save(user);
//        return "registration successful";
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//        Authentication authentication = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return "redirect:/";
//    }
//
//    @GetMapping("/hello")
//    public String hello(){
//        return "hello";
//    }
}
