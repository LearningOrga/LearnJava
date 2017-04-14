package com.backend.service;

import java.util.List;
import java.util.Map;

import com.backend.model.PlayResult;

public interface PlayResultService {

List<PlayResult> findAllRecords();
	
	List<PlayResult> findAllRecordsByUserId(int userId);
	
	List<PlayResult> findAllRecordsByParams(Map params);
			
	void updateResult(PlayResult result);
	
	void updateByResult(List<PlayResult> result);
	
	void savePlayResult(PlayResult result);
	
	void savePlayResultByUserId(PlayResult result, String id);
	
	void savePlayResultByParam(PlayResult result, Map params);
	
	double  getTotalInvestedPointsByUserId(int userId);
	
	double  getTotalEarnedPointsByUserId(int userId);
	
	int  getTotalWins(int userId);
	int  getTotalLoss(int userId);
	
	int  getTotalRulewWins(int ruleId, int userId);
	
	void removePlayByIds(Map params);
}
