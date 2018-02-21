package com.ipl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipl.dao.AbstractDao;
import com.ipl.dao.TeamDao;
import com.ipl.model.Team;

@Service("teamService")
@Transactional
public class TeamServiceImpl  implements TeamService{

	@Autowired
	private TeamDao teamDao;
	
	@Override
	public void saveTeam(Team team) {
		teamDao.saveTeam(team);
		
	}

	@Override	
	public List<Team> findAllTeams() {
	return	teamDao.findAllTeams();
	}

	@Override	
	public Team findTeamById(int teamId) {
		return teamDao.findTeamById(teamId);
	}

	
	
}
