package com.backend.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.backend.model.Team;

@Repository("teamDao")
public class TeamDaoImpl extends AbstractDao implements TeamDao{

	@Override
	public void saveTeam(Team team) {
		persist(team);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Team> findAllTeams() {
		Criteria criteria = getSession().createCriteria(Team.class);
		//("here");
		return (List<Team>) criteria.list();
	}

	@Override	
	public Team findTeamById(int teamId) {
		Criteria criteria = getSession().createCriteria(Team.class);		
		criteria.add(Restrictions.eq("id",teamId));
		Team retVal = (Team) criteria.uniqueResult();		
		return retVal;
	}

	
	
}
