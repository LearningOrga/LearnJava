package com.backend.dao;

import java.util.List;

import com.backend.model.Rule;

public interface RuleDao {

	void saveRule(Rule rule);
	
	List<Rule> findAllRules();
			
	Rule findRuleById(int ruleId);
	
	
}
