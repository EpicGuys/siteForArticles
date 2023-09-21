package com.epicGuys.app.articles.config;

import com.epicGuys.app.articles.repository.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and().build();
//        return http.addFilterBefore(new CustomAuthorizationFilter(), BasicAuthenticationFilter.class) // добавляем наш фильтр перед BasicAuthenticationFilter
//                .authorizeRequests()
//                .antMatchers("/public/**").permitAll() // доступ к публичным ресурсам
//                .antMatchers("/admin/**").hasRole("ADMIN") // доступ к ресурсам администратора только для пользователей с ролью ADMIN
//                .anyRequest().authenticated() // все остальные запросы требуют аутентификации
//                .and()
//                .formLogin() // настройка формы логина
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout() // настройка выхода из системы
//                .logoutUrl("/logout")
//                .permitAll();
    }
}
