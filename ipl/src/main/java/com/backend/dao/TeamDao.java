package com.backend.dao;

import java.util.List;

import com.backend.model.Team;

public interface TeamDao {

	void saveTeam(Team team);
	
	List<Team> findAllTeams();
			
	Team findTeamById(int teamId);
	
	
	
	
}
