package com.backend.dao;

import java.util.List;

import com.backend.model.MatchResult;

public interface MatchResultDao {
		
	List<MatchResult> findAllRecords();
	
	List<MatchResult> findAllRecordsByMatchId(int matchId);
	
	List<MatchResult> findAllRecordsByRuleId(int ruleId);			
	
	MatchResult findAllRecordsByRuleIdnadMatchId(int ruleId,int matchId);
	
	void saveMatchResult(MatchResult result);
	
	void updateMatchResult(MatchResult result);
	
	
	
}
