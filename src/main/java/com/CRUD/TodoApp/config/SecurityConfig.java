package com.CRUD.TodoApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    //list of URL that can be accessed without authentication. Mostly swagger access.
    private String[] Allowed_URL = {
            "/swagger-ui.html", "/v2/api-docs", "/swagger-ui/**",
            "/swagger-resources/**", "/configuration/ui",
            "/configuration/security", "/webjars/**"
    };


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers(Allowed_URL).permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }

    //encrypts user password.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }
}
