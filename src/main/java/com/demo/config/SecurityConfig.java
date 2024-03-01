package com.demo.config;

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
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    UserDetailsService getDetailsService() {
		return new CustomUserDetailsService();
	}
    
    @Bean
    DaoAuthenticationProvider getAuthenticationProvider() {
    	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    	authenticationProvider.setUserDetailsService(getDetailsService());
    	authenticationProvider.setPasswordEncoder(passwordEncoder());
    	return authenticationProvider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests().requestMatchers("/", "/login", "/register", "/save", "/js/**", "/css/**", "/images/**", "/fonts/**")
                .permitAll().anyRequest().authenticated().and().formLogin(login -> login.loginPage("/login").loginProcessingUrl("/userLogin").defaultSuccessUrl("/user/profile").permitAll());
    	/*
    	.requestMatchers("/user/**").authenticated().and().formLogin()
    	.loginPage("/login").loginProcessingUrl("/userLogin")
    	.defaultSuccessUrl("/user/profile").permitAll();*/
    	return http.build();
    	
    }
}
