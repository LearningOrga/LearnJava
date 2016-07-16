package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dao.MatchResultDao;
import com.backend.model.MatchResult;

@Service("matchResultService")
@Transactional
public class MatchResultServiceImpl implements MatchResultService {

	@Autowired
	private MatchResultDao dao;
	
	@Override
	public List<MatchResult> findAllRecords() {
		return dao.findAllRecords();
	}

	@Override
	public List<MatchResult> findAllRecordsByMatchId(int matchId) {
		return dao.findAllRecordsByMatchId(matchId);
	}

	@Override
	public List<MatchResult> findAllRecordsByRuleId(int ruleId) {
		return dao.findAllRecordsByRuleId(ruleId);
	}

	@Override
	public void saveMatchResult(MatchResult result) {
		dao.saveMatchResult(result);

	}

	@Override
	public MatchResult findAllRecordsByRuleIdnadMatchId(int ruleId, int matchId) {
		return dao.findAllRecordsByRuleIdnadMatchId(ruleId, matchId);
	
	}

	@Override
	public void updateMatchResult(MatchResult result) {
		dao.updateMatchResult(result);
		
	}

}
