package com.backend.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.backend.model.User;

@Repository("loginMasterDao")
public class UserDaoImpl extends AbstractDao implements UserDao{

	@Override
	public void saveUser(User match) {
		persist(match);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = getSession().createCriteria(User.class);
		//criteria.addOrder(Order.desc("availablePoints"));
		return (List<User>) criteria.list();
	}

	@Override	
	public User findUserByName(String name) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("loginName",name));
		return (User) criteria.uniqueResult();
	}
	
	@Override	
	public User findUserById(int userId) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("id",userId));
		return (User) criteria.uniqueResult();
	}

	@Override
	public void updateUser(User login) {
		getSession().update(login);
		
	}

	@Override
	public void deleteUserById(int userId) {
		
		getSession().delete(findUserById(userId));
		//Query query = getSession().createSQLQuery("delete from user_master where LOGIN_ID = :userId");
		//query.setInteger("LOGIN_ID", userId);
		//query.executeUpdate();
		
	}

	
	
}
