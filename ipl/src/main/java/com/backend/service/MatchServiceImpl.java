package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dao.MatchDao;
import com.backend.model.Match;

@Service("matchMasterService")
@Transactional
public class MatchServiceImpl implements MatchService{

	@Autowired
	private MatchDao dao;

	@Override
	public void saveMatch(Match match) {
		dao.saveMatch(match);
		
	}

	@Override
	public List<Match> findAllMatches() {
		return dao.findAllMatches();
	}

	@Override
	public Match findMatchById(int matchId) {
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
