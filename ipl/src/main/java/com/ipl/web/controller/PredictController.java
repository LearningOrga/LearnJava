package com.ipl.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.backend.model.Predict;
import com.backend.model.Team;
import com.backend.model.User;
import com.backend.service.PredictService;
import com.backend.service.TeamService;
import com.backend.service.UserService;

@RestController
public class PredictController {

	@Autowired
	PredictService predictService;

	@Autowired
	TeamService teamService;

	@Autowired
	UserService loginMasterService;

	@RequestMapping(value = "/predict/all", method = RequestMethod.GET)
	public @ResponseBody
	List<Predict> getAllPredicts(ModelMap model) {

		List<Predict> predicts = predictService.findAllPredicts();
		return predicts;
	}

	@RequestMapping(value = { "/addGoldenPredicts" }, method = RequestMethod.GET)
	public ModelAndView addGoldenPredicts(HttpServletRequest request,ModelMap model) {
		
		ModelAndView mav = new ModelAndView("add_golden_predicts");
		mav.addObject(model);
		return mav;
		
	}

	@RequestMapping(value = { "/viewGoldenPredicts" }, method = RequestMethod.GET)
	public ModelAndView viewGoldenPredicts(ModelMap model) {
		ModelAndView mav = new ModelAndView("view_all_golden_predicts");
		mav.addObject(model);
		return mav;
		
	}

	@RequestMapping(value = "/predict/add", method = RequestMethod.GET)
	public @ResponseBody
	List<Predict> addPredict(ModelMap model,
			@RequestParam("userId") int userId,
			@RequestParam("qmatchId1") int qmatchId1,
			@RequestParam("qmatchId2") int qmatchId2,
			@RequestParam("qmatchId3") int qmatchId3,
			@RequestParam("qmatchId4") int qmatchId4,
			@RequestParam("smatchId1") int smatchId1,
			@RequestParam("smatchId2") int smatchId2,
			@RequestParam("fmatchId1") int fmatchId1

	) {
		// ("I am here");
		Predict predict;
		List<Predict> predicted = new ArrayList<Predict>();

		User user = loginMasterService.findUserById(userId);
		predict = predictService.findByUserId(userId);
		if (predict == null) {

			Team one = teamService.findTeamById(qmatchId1);
			Team two = teamService.findTeamById(qmatchId2);
			Team three = teamService.findTeamById(qmatchId3);
			Team four = teamService.findTeamById(qmatchId4);
			Team sem1 = teamService.findTeamById(smatchId1);
			Team sem2 = teamService.findTeamById(smatchId2);
			Team finalt = teamService.findTeamById(fmatchId1);

			predict = new Predict(user, one, two, three, four, sem1, sem2,
					finalt);
			predictService.savePredict(predict);
		} else {// update

			Team one = teamService.findTeamById(qmatchId1);
			Team two = teamService.findTeamById(qmatchId2);
			Team three = teamService.findTeamById(qmatchId3);
			Team four = teamService.findTeamById(qmatchId4);
			Team sem1 = teamService.findTeamById(smatchId1);
			Team sem2 = teamService.findTeamById(smatchId2);
			Team finalt = teamService.findTeamById(fmatchId1);

			predict.setQfteam1(one);
			predict.setQfteam2(two);
			predict.setQfteam3(three);
			predict.setQfteam4(four);
			predict.setSfteam1(sem1);
			predict.setSfteam2(sem2);
			predict.setFinalWinningTeam(finalt);
			predictService.updatePredict(predict);
		}
		predicted.add(predict);
		return predicted;
	}

	/*
	 * @RequestMapping(value = "/predict/reconcile", method = RequestMethod.GET)
	 * public void updatePredictResult(ModelMap model,
	 * 
	 * @RequestParam("qmatchId1Res") String qmatchId1Res,
	 * 
	 * @RequestParam("qmatchId2Res") String qmatchId2Res,
	 * 
	 * @RequestParam("qmatchId3Res") String qmatchId3Res,
	 * 
	 * @RequestParam("qmatchId4Res") String qmatchId4Res,
	 * 
	 * @RequestParam("smatchId1Res") String smatchId1Res,
	 * 
	 * @RequestParam("smatchId2Res") String smatchId2Res,
	 * 
	 * @RequestParam("fialTeamIdRes") String fialTeamIdRes
	 * 
	 * ) {
	 * 
	 * List<User> users = loginMasterService.findAllUsers(); for (User user :
	 * users) { Predict predict = predictService.findByUserId(user.getId());
	 * predict.setQfTeam1Result(qmatchId1Res);
	 * predict.setQfTeam2Result(qmatchId2Res);
	 * predict.setQfTeam3Result(qmatchId3Res);
	 * predict.setQfTeam4Result(qmatchId4Res);
	 * 
	 * predict.setSfTeam1Result(smatchId1Res);
	 * predict.setSfTeam2Result(smatchId2Res);
	 * 
	 * predict.setFinalWinningTeamResult(fialTeamIdRes);
	 * predictService.updatePredict(predict); }
	 * 
	 * }
	 */
/*
	@RequestMapping(value = "/predict/update/{teamIds}", method = RequestMethod.GET)
	public void updatePredictResult(ModelMap model,
			@PathVariable("teamIds") String teamIds) {

		String qmatchId1Res = teamIds.substring(0, 1);
		String qmatchId2Res = teamIds.substring(1, 2);
		String qmatchId3Res = teamIds.substring(2, 3);
		String qmatchId4Res = teamIds.substring(3, 4);
		String smatchId1Res = teamIds.substring(4, 5);
		String smatchId2Res = teamIds.substring(5, 6);
		String fialTeamIdRes = teamIds.substring(6, 7);

		Team one = teamService.findTeamById(Integer.parseInt(qmatchId1Res));
		Team two = teamService.findTeamById(Integer.parseInt(qmatchId2Res));
		Team three = teamService.findTeamById(Integer.parseInt(qmatchId3Res));
		Team four = teamService.findTeamById(Integer.parseInt(qmatchId4Res));
		Team sem1 = teamService.findTeamById(Integer.parseInt(smatchId1Res));
		Team sem2 = teamService.findTeamById(Integer.parseInt(smatchId2Res));
		Team finalt = teamService.findTeamById(Integer.parseInt(fialTeamIdRes));

		List<User> users = loginMasterService.findAllUsers();
		Predict predict = null;
		for (User user : users) {
			predict = predictService.findByUserId(user.getId());
			if (predict != null) {

				predict.setQfTeam1Result(one);
				predict.setQfTeam2Result(two);
				predict.setQfTeam3Result(three);
				predict.setQfTeam4Result(four);

				predict.setSfTeam1Result(sem1);
				predict.setSfTeam2Result(sem2);

				predict.setFinalWinningTeamResult(finalt);
				predictService.updatePredict(predict);
			}
		}

	}
*/
	@RequestMapping(value = "/predict/{userId}", method = RequestMethod.GET)
	public @ResponseBody
	Predict getPredictByUserId(ModelMap model,
			@PathVariable("userId") int userId) {
		// ("I am here");
		Predict predict = predictService.findByUserId(userId);

		return predict;
	}

}