package com.backend.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.backend.model.Match;

@Repository("matchMasterDao")
public class MatchDaoImpl extends AbstractDao implements MatchDao{

	@Override
	public void saveMatch(Match match) {
		persist(match);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Match> findAllMatches() {
		Criteria criteria = getSession().createCriteria(Match.class);
		return (List<Match>) criteria.list();
	}

	@Override
	
	public Match findMatchById(int matchId) {
		Criteria criteria = getSession().createCriteria(Match.class);
		criteria.add(Restrictions.eq("id",matchId));
		return (Match) criteria.uniqueResult();
	}

	@Override
	public void updateMatch(Match match) {
		getSession().update(match);
		
	}

	@Override
	public void removeMatchById(int matchId) {
		getSession().delete(findMatchById(matchId));
		
	}

	
	
}
