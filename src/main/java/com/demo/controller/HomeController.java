package com.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.entity.User;
import com.demo.repository.UserRepo;
import com.demo.services.UserServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired private UserServices services;
	@Autowired private UserRepo repo;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/user/home")
	public String home() {
		return "home";
	}
	@GetMapping("/user/profile")
	public String profile(Principal principal, Model model) {
		String email = principal.getName();
		User user = repo.findByEmail(email);
		model.addAttribute("user",user);
		return "profile";
	}
	
	@PostMapping("/save") 
	public String saveUser(@ModelAttribute User user, HttpSession session) {
		User saveUser = this.services.saveUser(user);
		if(saveUser!=null) {
			session.setAttribute("msg", "user register successfully");
		}else {
			session.setAttribute("msg", "something went wrong");
		}
		System.out.println(user);
		return "redirect:register";
	}

}
