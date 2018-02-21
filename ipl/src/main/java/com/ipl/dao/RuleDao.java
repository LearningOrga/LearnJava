package com.ipl.dao;

import java.util.List;

import com.ipl.model.Rule;

public interface RuleDao {

	void saveRule(Rule rule);
	
	List<Rule> findAllRules();
			
	Rule findRuleById(int ruleId);
	
	
}
