package com.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig{

	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder(10);
	}
	@Bean
	public UserDetailsService userDetailsServiceImpl()
	{
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.
				csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
										.requestMatchers("/public","/add").permitAll()
										.requestMatchers("/user/**","/user").hasRole("USER")
										.requestMatchers("/admin/**","/admin").hasRole("ADMIN")
										.anyRequest().authenticated())
				
				.formLogin(form -> form
								.loginPage("/login")
								.permitAll()
						);
					
		return http.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		
		daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImpl());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return daoAuthenticationProvider;
	}
	
	
}
