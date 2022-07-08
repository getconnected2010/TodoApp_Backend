package com.CRUD.TodoApp.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@SecurityScheme(name = "TodoAppApi", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class SecurityConfig {
    //list of URL that can be accessed without authentication. Mostly swagger access.
    private String[] Allowed_URL = {
            "/swagger-ui.html", "/V3/api-docs/**", "/swagger-ui/**",
            "/swagger-resources/**", "/configuration/ui", "/swagger-ui/index.html",
            "/configuration/security", "/webjars/**", "/webjars/swagger-ui/**",
            "/v3/api-docs/swagger-config", "/v3/api-docs", "/swagger-ui/index.html#/user-controller/addUser",
            "/user/add"
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
                .httpBasic()
                .and()
                .csrf()
                .and()
                .cors()
                .disable();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().antMatchers("/user/add");
    }

    //encrypts user password.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }
}
