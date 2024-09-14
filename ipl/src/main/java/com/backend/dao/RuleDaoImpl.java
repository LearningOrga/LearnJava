package com.backend.dao;

import com.backend.model.Rule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ruleDao")
public class RuleDaoImpl extends AbstractDao implements RuleDao{

	@Autowired
	private EntityManager entityManager;

	@Override
	public void saveRule(Rule rule) {
		persist(rule);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Rule> findAllRules() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Rule> cq = cb.createQuery(Rule.class);
		Root<Rule> match = cq.from(Rule.class);
		cq.select(match);
		return (List<Rule>) entityManager.createQuery(cq).getResultList();

	}

	@Override	
	public Rule findRuleById(int ruleId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Rule> cq = cb.createQuery(Rule.class);
		Root<Rule> match = cq.from(Rule.class);
		Predicate idPredicate = cb.equal(match.get("id"),ruleId);
		cq.where( idPredicate);
		TypedQuery<Rule> query = entityManager.createQuery(cq);
		return (Rule) query.getSingleResult();


	}

	
	
}
