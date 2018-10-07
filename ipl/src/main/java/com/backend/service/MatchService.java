package com.backend.service;

import java.util.List;

import com.backend.model.Match;

public interface MatchService {

	void saveMatch(Match match);
	
	void updateMatch(Match match);
	
	List<Match> findAllMatches();
			
	Match findMatchById(int matchId);
	
	void removeMatchById(int matchId);
}
