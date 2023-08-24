package com.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.security.entities.User;
import com.security.services.UserService;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userService.findByUsername(username);
		
		if(user == null)
		{
			throw new UsernameNotFoundException(username);
		}

		UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user);

		return userDetailsImpl;
	}

}
