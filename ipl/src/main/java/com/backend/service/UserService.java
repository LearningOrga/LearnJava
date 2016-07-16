package com.backend.service;

import java.util.List;

import com.backend.model.User;

public interface UserService {

	void saveUser(User user);
	
	List<User> findAllUsers();
			
	User findUserByName(String name);
	
	User findUserById(int userId);
	
	void deleteUserById(int userId);
	
	void updateUser(User login);
}
