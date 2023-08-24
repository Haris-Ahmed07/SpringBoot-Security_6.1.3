package com.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUsername(String username);
}
