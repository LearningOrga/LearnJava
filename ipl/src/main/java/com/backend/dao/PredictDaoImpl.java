package com.backend.dao;

import com.backend.model.Predict;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("predictDao")
public class PredictDaoImpl extends AbstractDao implements PredictDao{
	@Autowired
	private EntityManager entityManager;
	@Override
	public void savePredict(Predict predict) {
		persist(predict);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Predict> findAllPredicts() {


		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Predict> cq = cb.createQuery(Predict.class);
		Root<Predict> match = cq.from(Predict.class);
		cq.select(match);
		return (List<Predict>) entityManager.createQuery(cq).getResultList();


	}


	@Override
	public Predict findByUserId(int userId){

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Predict> cq = cb.createQuery(Predict.class);
		Root<Predict> match = cq.from(Predict.class);
		Predicate idPredicate = cb.equal(match.get("id"),userId);
		cq.where( idPredicate);
		TypedQuery<Predict> query = entityManager.createQuery(cq);
		return (Predict) query.getSingleResult();

		/*Criteria criteria = getSession().createCriteria(Predict.class);
		criteria.add(Restrictions.eq("userId.id",userId));
		return (Predict) criteria.uniqueResult();*/
	}
	@Override
	public void updatePredict(Predict predict){
		getSession().update(predict);
	}
	
}
