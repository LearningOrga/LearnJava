package com.ipl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.ipl.model.Match;

@Repository("matchMasterDao")

public class MatchDaoImpl extends AbstractDao implements MatchDao{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Override
	public void saveMatch(Match match) {
		persist(match);
		
	}

	@Override
	@SuppressWarnings("unchecked")

	public List<Match> findAllMatches() {
		logger.info("Calling db...");
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
