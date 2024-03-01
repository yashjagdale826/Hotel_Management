package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.demo.entity.User;
import com.demo.repository.UserRepo;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired private UserRepo repo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = repo.findByEmail(email);
		if(user==null) {
			throw new UsernameNotFoundException("User name not found"); 
		}else {
			return new CustomUserDetails(user);
		}
	}

}
