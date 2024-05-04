package com.backend.dao;

import com.backend.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("loginMasterDao")
public class UserDaoImpl extends AbstractDao implements UserDao{
	@Autowired
	private EntityManager entityManager;
	@Override
	public void saveUser(User user) {
		persist(user);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> match = cq.from(User.class);
		cq.select(match);
		return (List<User>) entityManager.createQuery(cq).getResultList();
	}

	@Override	
	public User findUserByName(String name) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> match = cq.from(User.class);
		Predicate idPredicate = cb.equal(match.get("loginName"),name);
		cq.where( idPredicate);
		TypedQuery<User> query = entityManager.createQuery(cq);
		return (User) query.getSingleResult();

	}
	
	@Override	
	public User findUserById(int userId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> match = cq.from(User.class);
		Predicate idPredicate = cb.equal(match.get("id"),userId);
		cq.where( idPredicate);
		TypedQuery<User> query = entityManager.createQuery(cq);
		return (User) query.getSingleResult();
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
