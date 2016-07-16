package com.ipl.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.backend.model.Match;
import com.backend.model.MatchResult;
import com.backend.service.MatchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MatchController {
	@Autowired
	MatchService matchMasterService;

	@RequestMapping(value = "/match/all", method = RequestMethod.GET)
	public @ResponseBody
	List<Match> getAllMatches(ModelMap model) {

		List<Match> matches = matchMasterService.findAllMatches();

		return matches;
	}

	@RequestMapping(value = "/addMatchPoints", method = RequestMethod.GET)
	public String addMatchPoints(HttpServletRequest request,
			@RequestParam("matchid") int matchId) {
		request.getSession().setAttribute("matchId", matchId);

		return "add_points";
	}

	@RequestMapping(value = "/viewMatchPoints", method = RequestMethod.GET)
	public String viewMatchPoints(HttpServletRequest request,
			@RequestParam("matchid") int matchId) {
		request.getSession().setAttribute("matchId", matchId);

		return "view_points";
	}

	@RequestMapping(value = "/matchResults", method = RequestMethod.GET)
	public String matchResult(HttpServletRequest request,
			@RequestParam("matchid") int matchId) {
		request.getSession().setAttribute("matchId", matchId);

		return "match_result";
	}

	@RequestMapping(value = "/matchResultAdmin", method = RequestMethod.GET)
	public String matchResultAdmin(HttpServletRequest request,
			@RequestParam("matchid") int matchId) {
		request.getSession().setAttribute("matchId", matchId);

		return "match_results_admin";
	}

	/*
	 * @RequestMapping(value = "/match/all", method = RequestMethod.GET) public
	 * ModelAndView getAllMatches(ModelMap model) throws JsonProcessingException
	 * {
	 * 
	 * ObjectMapper mapper = new ObjectMapper();
	 * 
	 * 
	 * List<Match> matches = matchMasterService.findAllMatches();
	 * 
	 * for (Match match : matches) { ////(match); }
	 * 
	 * String obj = mapper.writeValueAsString(matches); //("obj::"+obj);
	 * ModelAndView model1 = new ModelAndView("ipl_home");
	 * model1.addObject("obj", obj); return model1; }
	 */

	@RequestMapping(value = "/match/{matchId}", method = RequestMethod.GET)
	public @ResponseBody
	Match getMatchById(ModelMap model, @PathVariable("matchId") int matchId) {

		Match matche = matchMasterService.findMatchById(matchId);

		return matche;
	}

	@RequestMapping(value = "/deletematch/{matchId}", method = RequestMethod.GET)
	public @ResponseBody
	void DeleteMatchById(ModelMap model, @PathVariable("matchId") int matchId) {

		matchMasterService.removeMatchById(matchId);

	}

	@RequestMapping(value = "/match/add/{matchDate}/{matchDay}/{matchTime}/{matchDetails}/{matchVenue}/{matchStatus}", method = RequestMethod.GET)
	public @ResponseBody
	void addMatch(ModelMap model, @PathVariable("matchDate") String matchDate,
			@PathVariable("matchDay") String matchDay,
			@PathVariable("matchTime") String matchTime,
			@PathVariable("matchDetails") String matchDetails,
			@PathVariable("matchVenue") String matchVenue,
			@PathVariable("matchStatus") String matchStatus) {

		Match match = new Match(matchDate, matchDay, matchTime, matchDetails,
				matchVenue, matchStatus);

		matchMasterService.saveMatch(match);

	}

	@RequestMapping(value = "/match/UpdateStatus", method = RequestMethod.GET)
	public @ResponseBody
	List<String> updateMatchStatus(ModelMap model,
			@RequestParam("selMatchId") int matchId,
			@RequestParam("selMatchStatus") String matchStatus) {

		// ("Diabling/Enabling this....");

		Match match = matchMasterService.findMatchById(matchId);
		match.setMatchStatus(matchStatus);

		matchMasterService.updateMatch(match);

		// ("Diabling/Enabling this...."+match);
		Match updatedMatch = matchMasterService.findMatchById(matchId);
		// ("STATUS FROM UPDATED TABLE...."+updatedMatch.getMatchStatus());
		List retVal = new ArrayList();
		retVal.add(updatedMatch.getMatchStatus());

		return retVal;

	}
}