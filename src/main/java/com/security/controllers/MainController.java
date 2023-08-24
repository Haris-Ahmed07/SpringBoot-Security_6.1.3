package com.security.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.security.entities.User;
import com.security.services.UserService;


@Controller
public class MainController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@GetMapping("/public")
	@ResponseBody
	public String publicUser()
	{
		return "You are a Public User";
	}
	
	@GetMapping("/user")
	@ResponseBody
	public String normalUser()
	{
		return"You are an User";
	}
	
	@GetMapping("/admin")
	@ResponseBody
	public String adminUser()
	{
		return "You are an Admin User";
	}
	
	@PostMapping("/add")
	public void addUser(@RequestBody User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.save(user);
	}
	
}
