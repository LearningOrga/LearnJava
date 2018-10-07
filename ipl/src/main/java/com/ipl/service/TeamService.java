package com.ipl.service;

import java.util.List;

import com.ipl.model.Team;

public interface TeamService {

	void saveTeam(Team team);
	
	List<Team> findAllTeams();
			
	Team findTeamById(int teamId);
	
	
}
