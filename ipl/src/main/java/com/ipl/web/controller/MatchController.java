package com.ipl.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Match;
import com.backend.service.MatchService;

@RestController
@RequestMapping("/match")
public class MatchController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MatchService matchMasterService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody
	List<Match> getAllMatches(ModelMap model) {

		List<Match> matches = matchMasterService.findAllMatches();

		return matches;
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

	@RequestMapping(value = "/{matchId}", method = RequestMethod.GET)
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

	@RequestMapping(value = "/add/{matchDate}/{matchDay}/{matchTime}/{matchDetails}/{matchVenue}/{matchStatus}", method = RequestMethod.GET)
	@Secured ("ROLE_ADMIN")
	public @ResponseBody
	void addMatch(ModelMap model, @PathVariable("matchDate") String matchDate,
			@PathVariable("matchDay") String matchDay,
			@PathVariable("matchTime") String matchTime,
			@PathVariable("matchDetails") String matchDetails,
			@PathVariable("matchVenue") String matchVenue,
			@PathVariable("matchStatus") String matchStatus) {
		
		logger.debug("Entry");

		Match match = new Match(matchDate, matchDay, matchTime, matchDetails,
				matchVenue, matchStatus);

		matchMasterService.saveMatch(match);
		logger.debug("Exit"+match);
	}

	@RequestMapping(value = "/UpdateStatus", method = RequestMethod.GET)
	@Secured ("ROLE_ADMIN")
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