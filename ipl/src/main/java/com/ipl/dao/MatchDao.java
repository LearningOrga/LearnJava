package com.ipl.dao;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.ipl.model.Match;

public interface MatchDao {

	void saveMatch(Match match);
	void updateMatch(Match match);
	
	
	List<Match> findAllMatches();
			
	Match findMatchById(int matchId);
	
	void removeMatchById(int matchId);
}
