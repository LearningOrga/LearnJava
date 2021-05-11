package com.backend.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * Abstract dao cfor session factory etc
 *
 */
public abstract class AbstractDao {

	/*@Autowired
	private SessionFactory sessionFactory;
*/
	@Autowired
	private EntityManager entityManager;
	//added comment
	protected Session getSession() {
		Session session = entityManager.unwrap(Session.class);

		return session;
		//return sessionFactory.getCurrentSession();
	}

	public void persist(Object entity) {
		getSession().persist(entity);
	}

	public void delete(Object entity) {
		getSession().delete(entity);
	}
}
