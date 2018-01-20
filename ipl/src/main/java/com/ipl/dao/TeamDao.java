package com.ipl.dao;

import java.util.List;

import com.ipl.model.Team;

public interface TeamDao {

	void saveTeam(Team team);
	
	List<Team> findAllTeams();
			
	Team findTeamById(int teamId);
	
	
	
	
}
