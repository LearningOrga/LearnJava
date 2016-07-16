package com.backend.dao;

import java.util.List;

import com.backend.model.Employee;
import com.backend.model.User;

public interface UserDao {

	void saveUser(User login);
	
	List<User> findAllUsers();
			
	User findUserByName(String userName);
	
	User findUserById(int userId);
	
	void deleteUserById(int userId);
	
	void updateUser(User login);
	
	
}
