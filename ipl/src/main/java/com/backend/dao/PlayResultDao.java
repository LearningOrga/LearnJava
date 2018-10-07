package com.backend.dao;

import java.util.List;
import java.util.Map;

import com.backend.model.PlayResult;

public interface PlayResultDao {

	List<PlayResult> findAllRecords();

	List<PlayResult> findAllRecordsByUserId(int userId);

	List<PlayResult> findAllRecordsByParams(Map params);

	void updateByResult(List<PlayResult> result);

	void updateResult(PlayResult result);

	void savePlayResult(PlayResult result);

	void savePlayResultByUserId(PlayResult result, String id);

	void savePlayResultByParam(PlayResult result, Map params);

	double getTotalInvestedPointsByUserId(int userId);

	double getTotalEarnedPointsByUserId(int userId);

	int getTotalWins(int userId);

	int getTotalLoss(int userId);
	
	int getTotalRulewWins(int ruleId, int userId);
	
	void removePlayByIds(Map params);
	
	

}
