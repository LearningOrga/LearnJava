package com.ipl.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Rule;
import com.backend.service.RuleService;

@RestController
@RequestMapping("/rule")
public class RuleController {
	@Autowired
	RuleService ruleService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody
	List<Rule> getAllRules(ModelMap model) {

		List<Rule> rules = ruleService.findAllRules();
		
		return rules;
	}

	@RequestMapping(value = "/{ruleId}", method = RequestMethod.GET)
	public @ResponseBody
	Rule getRuleById(ModelMap model,
			@PathVariable("ruleId") int ruleId) {

		Rule rule = ruleService.findRuleById(ruleId);

		return rule;
	}
	
}