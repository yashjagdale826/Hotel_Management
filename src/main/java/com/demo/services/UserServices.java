package com.demo.services;

import com.demo.entity.User;

public interface UserServices {
	
	User saveUser(User user);
	
	public void removeSessionMsg();
	

}
