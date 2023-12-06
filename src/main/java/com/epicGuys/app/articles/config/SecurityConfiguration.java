package com.epicGuys.app.articles.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors((cors)->{cors
			.disable();
		});
		http.csrf((crfs)->{crfs
			.disable();
		});
		http.authorizeHttpRequests((authorize) -> {authorize
<<<<<<< HEAD
//				.requestMatchers("/epic-guys/users/**").hasRole("ADMIN")
//				.requestMatchers("/epic-guys/articles/writer/**").hasRole("WRITER")
//				.requestMatchers("/login").anonymous()
//				.requestMatchers("/login?logout").authenticated()
=======
				.requestMatchers("/epic-guys/users/**").hasRole("ADMIN")
				.requestMatchers("/epic-guys/articles/writer/**").hasRole("WRITER")
				//.requestMatchers("/login?logout").authenticated()
				//.requestMatchers("/login").anonymous()
>>>>>>> af9bd7417618341fa7d48a1edd5a369832981a10
				.requestMatchers("/**").permitAll();
		});
		http.formLogin((form)->{form
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.failureUrl("/login?error");
		});

		return http.build();
	}	
}