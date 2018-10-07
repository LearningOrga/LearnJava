package com.ipl.service;

import java.util.List;

import com.ipl.model.MatchResult;

public interface MatchResultService {
	
List<MatchResult> findAllRecords();
	
	List<MatchResult> findAllRecordsByMatchId(int matchId);
	
	List<MatchResult> findAllRecordsByRuleId(int ruleId);	
	
	MatchResult findAllRecordsByRuleIdnadMatchId(int ruleId,int matchId);
	
	void saveMatchResult(MatchResult result);
	
	void updateMatchResult(MatchResult result);

}
