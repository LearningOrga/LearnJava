package com.ipl.service;

import java.util.List;

import com.ipl.model.User;

public interface UserService {

	void saveUser(User user);
	
	List<User> findAllUsers();
			
	User findUserByName(String name);
	
	User findUserById(int userId);
	
	void deleteUserById(int userId);
	
	void updateUser(User login);
}
