package com.security.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.entities.User;
import com.security.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public UserService() {
		super();
	}
	
	public void save(User user)
	{
		userRepository.save(user);
	}
	
	public User findByUsername(String username)
	{
		return userRepository.findByUsername(username);
	}
	
	public List<User> findAll()
	{
		return userRepository.findAll();
	}
	
	public List<User> getUsers()
	{
		List<User> users = userRepository.findAll();
		
		List<User> UserRole = users.stream()
				.filter(user -> user.getRole().equals("ROLE_USER"))
				.collect(Collectors.toList());
		
		return UserRole;
	}
	
	public List<User> getAdmins()
	{
		List<User> users = userRepository.findAll();
		
		List<User> AdminRole = users.stream()
				.filter(user -> user.getRole().equals("ROLE_ADMIN"))
				.collect(Collectors.toList());
		
		return AdminRole;
	}
}
