package com.ipl.service;

import java.util.List;

import com.ipl.model.Rule;

public interface RuleService {

	void saveRule(Rule rule);
	
	List<Rule> findAllRules();
			
	Rule findRuleById(int ruleId);
	
	
}
