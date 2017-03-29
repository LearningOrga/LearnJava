package com.ipl.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Team;
import com.backend.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController {

	@Autowired
	TeamService teamService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody
	List<Team> getAllTeams(ModelMap model) {

		List<Team> teams = teamService.findAllTeams();

		return teams;
	}

	@RequestMapping(value = "/{teamId}", method = RequestMethod.GET)
	public @ResponseBody
	Team getTeamById(ModelMap model, @PathVariable("teamId") int teamId) {

		Team team = teamService.findTeamById(teamId);

		return team;
	}

	@RequestMapping(value = "/add/{teamName}/{homeVenue}/{captain}", method = RequestMethod.GET)
	public @ResponseBody
	void addTeam(ModelMap model, @PathVariable("teamName") String teamName,
			@PathVariable("homeVenue") String homeVenue,
			@PathVariable("captain") String captain) {

		Team team = new Team();
		team.setTeamName(teamName);
		team.setTeamHomeVenue(homeVenue);
		team.setTeamCaptain(captain);

		teamService.saveTeam(team);

	}
}