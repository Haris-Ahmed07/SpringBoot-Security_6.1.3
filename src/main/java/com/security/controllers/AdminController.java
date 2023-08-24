package com.security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.entities.User;
import com.security.services.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;	


	@GetMapping("/get-admin/{username}")
	public User getAdminByUsername(@PathVariable("username") String username)
	{
		return userService.findByUsername(username);
	}
	
	@GetMapping("/get-admin")
	public List<User> getAdmins()
	{
		return userService.getAdmins();
	}
}
