package com.backend.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.backend.model.Match;
import com.backend.model.MatchResult;
import com.backend.model.PlayResult;
import com.backend.model.Rule;
import com.backend.model.Summary;
import com.backend.model.User;
import com.backend.service.MatchResultService;
import com.backend.service.MatchService;
import com.backend.service.PlayResultService;
import com.backend.service.RuleService;
import com.backend.service.UserService;
import com.backend.model.fe.*;

@RestController
@RequestMapping("/playResult")
public class PlayResultController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PlayResultService playResultService;

	@Autowired
	UserService userService;

	@Autowired
	RuleService ruleService;

	@Autowired
	MatchService matchService;

	@Autowired
	MatchResultService matchResultService;

	// TODO: why we need this??? 2017 why

	@RequestMapping(value = "/deleteDuplicate", method = RequestMethod.GET)
	public ModelAndView deleteDuplicate(ModelMap model) {

		List<Match> allMatches = matchService.findAllMatches();

		for (Match match : allMatches) {

			List<User> users = userService.findAllUsers();
			for (User user : users) {

				List<Rule> rules = ruleService.findAllRules();

				for (Rule rule : rules) {

					Map param = new HashMap();
					param.put("matchId", match.getId());
					param.put("userId", user.getId());
					param.put("ruleId", rule.getId());

					playResultService.removePlayByIds(param);

				}
			}

		}

		ModelAndView mav = new ModelAndView("admin");
		mav.addObject(model);
		return mav;
	}

	// TODO:use delete
	@RequestMapping(value = "/deleteAllbyMatchId/{matchId}", method = RequestMethod.GET)
	@Secured("ROLE_ADMIN")
	public ModelAndView deleteallByMatchid(ModelMap model,
			@PathVariable("matchId") int matchId) {

		Map param = new HashMap();
		param.put("matchId", matchId);

		playResultService.removePlayByIds(param);

		ModelAndView mav = new ModelAndView("admin");
		mav.addObject(model);
		return mav;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody
	List<PlayResult> getAllRecords(ModelMap model) {

		List<PlayResult> results = playResultService.findAllRecords();

		return results;
	}

	@RequestMapping(value = "/allForSummary", method = RequestMethod.GET)
	public @ResponseBody
	List<Summary> getALlRecordsForSummary(ModelMap model) {

		List<Summary> userSumm = new ArrayList<Summary>();
		Summary summary = null;

		List<User> users = userService.findAllUsers();
		for (User user : users) {

			int userId = user.getId();
			int numberofWins;
			int numberOfLoss;
			DecimalFormat df = new DecimalFormat("#.##");

			summary = new Summary();

			summary.setUserId(userId);
			summary.setUserName(user.getLoginName());
			summary.setTotalPoints(user.getAvailablePoints());
			summary.setTotalPointsInvested(playResultService
					.getTotalInvestedPointsByUserId(userId));
			summary.setTotalPointsEarned(playResultService
					.getTotalEarnedPointsByUserId(userId));

			summary.setRule1Wins(playResultService.getTotalRulewWins(1, userId));
			summary.setRule2Wins(playResultService.getTotalRulewWins(2, userId));

			numberofWins = playResultService.getTotalWins(userId);
			numberOfLoss = playResultService.getTotalLoss(userId);

			summary.setTotalNumberWins(numberofWins);
			int totalPlayed = numberofWins + numberOfLoss;
			summary.setTotalNumberPredicted(totalPlayed);
			if (totalPlayed != 0) {
				double winnLossPer = 0;
				winnLossPer = (double) numberofWins / totalPlayed;

				winnLossPer = winnLossPer * 100;
				summary.setWinLossPer(Double.parseDouble(df.format(winnLossPer)));
			}

			userSumm.add(summary);

		}

		return userSumm;
	}

	@RequestMapping(value = "/{matchId}/{ruleId}", method = RequestMethod.GET)
	public @ResponseBody
	List<PlayResult> getPlayResultByMatchIdAndRuleId(ModelMap model,
			@PathVariable("matchId") int matchId,
			@PathVariable("ruleId") int ruleId) {

		Map param = new HashMap();
		param.put("matchId", matchId);
		param.put("ruleId", ruleId);

		List<PlayResult> results = playResultService
				.findAllRecordsByParams((param));

		playResultService.updateByResult(calculateIndicativePoints(results,
				matchId));

		return results;
	}

	@RequestMapping(value = "/byMatchId", method = RequestMethod.GET)
	public @ResponseBody
	List<PlayResult> getPlayResultByUserId(ModelMap model,
			@RequestParam("matchId") int matchId) {

		Map param = new HashMap();
		param.put("matchId", matchId);

		List<PlayResult> results = playResultService
				.findAllRecordsByParams((param));

		return results;
	}

	@RequestMapping(value = "/reconcileAllUsersPoints", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	@ResponseBody
	public List<User> reconcilePlayResultByUserId(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestBody Match matchReq) {

		List<User> users = new ArrayList();

		users = userService.findAllUsers();
		for (User user : users) {
			Map params = new HashMap();
			params.put("matchId", matchReq.getId());
			params.put("userId", user.getId());

			List<PlayResult> results = playResultService
					.findAllRecordsByParams(params);
			Double totalLossOrWin = 0.0;
			for (PlayResult result : results) {
				Double totalPointsEarned = result.getTotalPointsEarned();
				if (totalPointsEarned == null) {
					totalPointsEarned = 0.0;
				}

				totalLossOrWin = totalLossOrWin + totalPointsEarned;
			}

			user.setAvailablePoints(user.getAvailablePoints() + totalLossOrWin);
			userService.updateUser(user);
		}

		// TODO: this list may not be updated so if you are planning to use get
		// one
		return users;
	}

	@RequestMapping(value = "/reconcile", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public @ResponseBody
	List<PlayResult> getReconcileData(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestBody MatchResult matchResultReq) {

		// TODO: added in 2017 to remove duplicate rows during reconcile

		// TODO: update match result in play result from match result
		MatchResult matchresult = matchResultService
				.findAllRecordsByRuleIdnadMatchId(matchResultReq.getRuleId()
						.getId(), matchResultReq.getMatchId().getId());

		Map param = new HashMap();
		param.put("matchId", matchResultReq.getMatchId().getId());
		param.put("ruleId", matchResultReq.getRuleId().getId());

		List<PlayResult> results = new ArrayList();

		results = playResultService.findAllRecordsByParams((param));

		for (PlayResult result : results) {
			result.setRuleResult(matchresult.getRuleResult());
			// TODO: this call not required but lets see
			// playResultService.updateResult(result);

		}

		playResultService.updateByResult(calculateTotalPoints(results,
				matchResultReq.getMatchId().getId(), matchResultReq.getRuleId()
						.getId()));

		return results;
	}

	/**
	 * THis method will be called from AJAX on the input page.
	 * 
	 * @param playResult
	 * @return
	 */
	private List<PlayResult> calculateIndicativePoints(
			List<PlayResult> playResult, int matchId) {
		Map pointsInvested = new HashMap();
		Map indicativePoints = new HashMap();

		Match match = matchService.findMatchById(matchId);
		String matchName = match.getMatchDetails();
		int indedofVs = matchName.indexOf("vs");

		String matchSplit1 = matchName.substring(0, indedofVs);
		String matchSplit2 = matchName.substring(indedofVs + 3);

		pointsInvested.put(matchSplit1.trim(), "0");
		pointsInvested.put(matchSplit2.trim(), "0");

		for (PlayResult result : playResult) {

			String ruleValue = result.getRuleValue();
			if (pointsInvested.containsKey(ruleValue)) {
				double tempPoints = Double.parseDouble(pointsInvested.get(
						ruleValue).toString());
				pointsInvested.put(ruleValue,
						tempPoints + result.getPointsInvested());
			}
		}

		// (pointsInvested);

		for (PlayResult result : playResult) {
			Set keyList = pointsInvested.keySet();
			Iterator keyListItr = keyList.iterator();
			String val1, val2;
			val1 = keyListItr.next().toString();
			val2 = keyListItr.next().toString();

			if (val1.equals(result.getRuleValue())) {
				double temp = result.getPointsInvested()
						/ Double.parseDouble(pointsInvested.get(
								result.getRuleValue()).toString());
				double temp1 = Double.parseDouble(pointsInvested.get(val2)
						.toString());
				double ptsInvested = result.getPointsInvested();
				double result2 = temp * temp1;
				double result3 = result2 + ptsInvested;
				result.setIndicativePoints(result3);

			} else {
				double temp = result.getPointsInvested()
						/ Double.parseDouble(pointsInvested.get(
								result.getRuleValue()).toString());
				double temp1 = Double.parseDouble(pointsInvested.get(val1)
						.toString());
				double ptsInvested = result.getPointsInvested();

				double result2 = temp * temp1;

				double result3 = result2 + ptsInvested;

				result.setIndicativePoints(result3);
			}

		}
		return playResult;
	}

	/**
	 * THis method will be called from Reconcile method
	 * 
	 * @param playResult
	 * @return
	 */
	private List<PlayResult> calculateTotalPoints(List<PlayResult> playResult,
			int matchId, int ruleId) {
		Map pointsInvested = new HashMap();
		Map indicativePoints = new HashMap();

		for (PlayResult result : playResult) {
			String ruleValue = result.getRuleValue();
			if (pointsInvested.containsKey(ruleValue)) {
				double tempPoints = Double.parseDouble(pointsInvested.get(
						ruleValue).toString());
				pointsInvested.put(ruleValue,
						tempPoints + result.getPointsInvested());
			} else {
				pointsInvested.put(ruleValue, result.getPointsInvested());
			}
		}

		String wininTeam = matchResultService.findAllRecordsByRuleIdnadMatchId(
				ruleId, matchId).getRuleResult();
		for (PlayResult result : playResult) {

			if (result.getRuleValue().equals(wininTeam)) {
				result.setResult("WIN");
			} else {
				result.setResult("LOSS");
			}

			Set keyList = pointsInvested.keySet();
			Iterator keyListItr = keyList.iterator();
			String val1, val2;

			int indedofVs = result.getMatchId().getMatchDetails().indexOf("vs");

			val1 = result.getMatchId().getMatchDetails()
					.substring(0, indedofVs).trim();
			val2 = result.getMatchId().getMatchDetails()
					.substring(indedofVs + 3).trim();

			if ((result.getRuleValue().equals(wininTeam))) {
				double temp, temp1;

				if (val1.equals(wininTeam)) {
					temp = result.getPointsInvested()
							/ Double.parseDouble(pointsInvested.get(val1)
									.toString());
					temp1 = Double.parseDouble(pointsInvested.get(val2)
							.toString());
				} else {
					temp = result.getPointsInvested()
							/ Double.parseDouble(pointsInvested.get(val2)
									.toString());
					temp1 = Double.parseDouble(pointsInvested.get(val1)
							.toString());
				}
				double ptsInvested = result.getPointsInvested();

				double result2 = temp * temp1;

				result.setTotalPointsEarned(result2);
			} else {

				result.setTotalPointsEarned(result.getPointsInvested() * -1);
			}

		}

		return playResult;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	List<PlayResult> getResultByPara(HttpServletRequest request,
			ModelMap model, @RequestBody PlaySelection playResultReq) {

		int ruleId = playResultReq.getRuleId();
		String selTeamName = playResultReq.getSelTeamName();
		int matchId = playResultReq.getMatchId();
		String pointsInvested = playResultReq.getPointsInvested();
		String selMatchTime = playResultReq.getSelMatchTime();

		List updatedPlayResult = null;
		Match match = matchService.findMatchById(matchId);
		User user = userService.findUserByName(getPrincipal());
		int userId = user.getId();
		if (match.getMatchStatus().equals("A")) {

			Map param = new HashMap();
			param.put("matchId", matchId);
			param.put("ruleId", ruleId);
			param.put("userId", userId);

			Map customParam = new HashMap();
			customParam.put("matchId", matchId);
			customParam.put("userId", userId);

			List playResultList = playResultService
					.findAllRecordsByParams(param);

			PlayResult playResult;

			if (playResultList.size() == 0) {

				playResult = new PlayResult();
				playResult.setUserId(user);
				playResult.setRuleId(ruleService.findRuleById(ruleId));
				playResult.setRuleValue(playResultReq.getRuleValue());
				playResult.setMatchId(matchService.findMatchById(matchId));
				playResult.setPointsInvested(Double.parseDouble(playResultReq
						.getPointsInvested().toString()));

				playResultService.savePlayResult(playResult);

			} else {
				playResult = (PlayResult) playResultList.get(0);
				playResult.setUserId(user);
				playResult.setRuleId(ruleService.findRuleById(ruleId));
				playResult.setRuleValue(playResultReq.getRuleValue());
				playResult.setMatchId(matchService.findMatchById(matchId));
				playResult.setPointsInvested(Double.parseDouble(playResultReq
						.getPointsInvested().toString()));

				playResultService.updateResult(playResult);
			}

			// return updated;

			getPlayResultByMatchIdAndRuleId(null, matchId, ruleId);

			updatedPlayResult = playResultService
					.findAllRecordsByParams(customParam);
			request.getSession().setAttribute("updatedPlayResult",
					updatedPlayResult);

		}
		return updatedPlayResult;
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}
