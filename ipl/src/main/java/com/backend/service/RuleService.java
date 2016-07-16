package com.backend.service;

import java.util.List;

import com.backend.model.Rule;

public interface RuleService {

	void saveRule(Rule rule);
	
	List<Rule> findAllRules();
			
	Rule findRuleById(int ruleId);
	
	
}
