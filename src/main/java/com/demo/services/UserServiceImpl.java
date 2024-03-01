package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.demo.entity.User;
import com.demo.repository.UserRepo;

import jakarta.servlet.http.HttpSession;


@Service
public class UserServiceImpl implements UserServices {

	@Autowired private UserRepo repo;
	
	@Autowired private BCryptPasswordEncoder encoder;
	
	
	@Override
	public User saveUser(User user) {
		user.setRole("ROLE_USER");
		String pass = encoder.encode(user.getPassword());
		user.setPassword(pass);
		// TODO Auto-generated method stub
		User save = repo.save(user);
		return save;
	}


	@Override
	public void removeSessionMsg() {
		// TODO Auto-generated method stub
		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg");
	}

	
}
