package com.backend.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.backend.model.Rule;

@Repository("ruleDao")
public class RuleDaoImpl extends AbstractDao implements RuleDao{

	@Override
	public void saveRule(Rule rule) {
		persist(rule);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Rule> findAllRules() {
		Criteria criteria = getSession().createCriteria(Rule.class);
		//("here");
		return (List<Rule>) criteria.list();
	}

	@Override	
	public Rule findRuleById(int ruleId) {
		Criteria criteria = getSession().createCriteria(Rule.class);
		criteria.add(Restrictions.eq("id",ruleId));
		return (Rule) criteria.uniqueResult();
	}

	
	
}
