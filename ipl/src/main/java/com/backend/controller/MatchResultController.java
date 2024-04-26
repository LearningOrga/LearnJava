package com.backend.controller;

import com.backend.model.MatchResult;
import com.backend.service.MatchResultService;
import com.backend.service.MatchService;
import com.backend.service.RuleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MatchResultController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

	@RequestMapping(value = "/matchResult/add", method = RequestMethod.POST)
	@Secured ("ROLE_ADMIN")
	public @ResponseBody
	List<MatchResult> addMatchResult(HttpServletRequest request,
									 HttpServletResponse response, ModelMap model, @RequestBody MatchResult matchResultReq) {
		 logger.info("Updating Playresult......");
		
		MatchResult matchResult;
		List<MatchResult> retVal = new ArrayList();
		
			matchResult = matchResultService.findAllRecordsByRuleIdnadMatchId(
					matchResultReq.getRuleId().getId(), matchResultReq.getMatchId().getId());
			if (matchResult != null) {

				// matchResult.setMatchId(matchService.findMatchById(matchId));
				// matchResult.setRuleId(ruleService.findRuleById(ruleId));
				matchResult.setRuleResult(matchResultReq.getRuleResult());
				matchResultService.updateMatchResult(matchResult);
			} else {

				matchResult = new MatchResult();
				matchResult.setMatchId(matchService.findMatchById(matchResultReq.getMatchId().getId()));
				matchResult.setRuleId(ruleService.findRuleById(matchResultReq.getRuleId().getId()));
				matchResult.setRuleResult(matchResultReq.getRuleResult());

				matchResultService.saveMatchResult(matchResult);
			}
			retVal = matchResultService.findAllRecordsByMatchId(matchResultReq.getMatchId().getId());
			 logger.info("Updating Playresult......done");
		return retVal;

	}

}