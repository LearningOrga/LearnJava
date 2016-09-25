package com.ipl.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.backend.model.MatchResult;
import com.backend.service.MatchResultService;
import com.backend.service.MatchService;
import com.backend.service.RuleService;

@Controller
public class MatchResultController {

	@Autowired
	RuleService ruleService;

	@Autowired
	MatchService matchService;

	@Autowired
	MatchResultService matchResultService;

	@RequestMapping(value = "/matchResult/all", method = RequestMethod.GET)
	public @ResponseBody
	List<MatchResult> getAllRecords(ModelMap model) {

		List<MatchResult> results = matchResultService.findAllRecords();
		return results;
	}

	@RequestMapping(value = "/matchResult", method = RequestMethod.GET)
	public @ResponseBody
	List<MatchResult> getMatchResultByMatchId(ModelMap model,
			@RequestParam("matchId") int matchId) {

		List<MatchResult> results = matchResultService
				.findAllRecordsByMatchId(matchId);

		return results;
	}

	@RequestMapping(value = "/matchResult/add", method = RequestMethod.GET)
	public @ResponseBody
	List<MatchResult> addMatchResult(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("selMatchId") int matchId,
			@RequestParam("selRuleId") int ruleId,
			@RequestParam("selTeamName") String ruleResult) {
		MatchResult matchResult;
		List<MatchResult> retVal = new ArrayList();
		
			matchResult = matchResultService.findAllRecordsByRuleIdnadMatchId(
					ruleId, matchId);
			if (matchResult != null) {

				// matchResult.setMatchId(matchService.findMatchById(matchId));
				// matchResult.setRuleId(ruleService.findRuleById(ruleId));
				matchResult.setRuleResult(ruleResult);
				matchResultService.updateMatchResult(matchResult);
			} else {

				matchResult = new MatchResult();
				matchResult.setMatchId(matchService.findMatchById(matchId));
				matchResult.setRuleId(ruleService.findRuleById(ruleId));
				matchResult.setRuleResult(ruleResult);

				matchResultService.saveMatchResult(matchResult);
			}
			retVal = matchResultService.findAllRecordsByMatchId(matchId);
		
		return retVal;

	}

}