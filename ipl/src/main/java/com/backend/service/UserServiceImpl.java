package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dao.UserDao;
import com.backend.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;

	@Override
	public void saveUser(User user) {
		dao.saveUser(user);
		
	}

	@Override
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	@Override
	public User findUserByName(String userName) {
		return dao.findUserByName(userName);
	}
	
	@Override
	public User findUserById(int userId) {
		return dao.findUserById(userId);
	}
	
	@Override
	public void updateUser(User login){
		dao.updateUser(login);
	}

	@Override
	public void deleteUserById(int userId) {
		dao.deleteUserById(userId);
		
	}
}
