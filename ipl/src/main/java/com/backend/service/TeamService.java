package com.backend.service;

import java.util.List;

import com.backend.model.Team;

public interface TeamService {

	void saveTeam(Team team);
	
	List<Team> findAllTeams();
			
	Team findTeamById(int teamId);
	
	
}
