package com.backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dao.MatchDao;
import com.backend.model.Match;

@Service("matchMasterService")
@Transactional
@CacheConfig(cacheNames = "match")
public class MatchServiceImpl implements MatchService{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 @CacheEvict(allEntries = true)
	 public void clearCache(){}

	@Autowired
	private MatchDao dao;

	@Override
	public void saveMatch(Match match) {
		dao.saveMatch(match);
		
	}

	@Override
	@Cacheable(value="match" , key="'matches'")
	public List<Match> findAllMatches() {
		logger.info("Calling dao...");
		return dao.findAllMatches();
	}

	@Override
	@Cacheable(value="match", key= "#matchId")
	public Match findMatchById(int matchId) {
		logger.info("Calling dao for matchId...");
		return dao.findMatchById(matchId);
	}

	@Override
	public void updateMatch(Match match) {
		dao.updateMatch(match);
		
	}

	@Override
	public void removeMatchById(int matchId) {
		dao.removeMatchById(matchId);
		
	}
	
	
}
