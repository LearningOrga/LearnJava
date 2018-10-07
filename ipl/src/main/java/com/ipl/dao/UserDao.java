package com.ipl.dao;

import java.util.List;

import com.ipl.model.Employee;
import com.ipl.model.User;

public interface UserDao {

	void saveUser(User login);
	
	List<User> findAllUsers();
			
	User findUserByName(String userName);
	
	User findUserById(int userId);
	
	void deleteUserById(int userId);
	
	void updateUser(User login);
	
	
}
