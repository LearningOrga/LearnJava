package com.ipl.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipl.dao.PlayResultDao;
import com.ipl.dao.UserDao;
import com.ipl.model.PlayResult;

@Service("playResultService")
@Transactional
@CacheConfig(cacheNames = "playResult")
public class PlayResultServiceImpl implements PlayResultService {
	
	@Autowired
	private PlayResultDao dao;
	

	@Override
	@Cacheable(value="playResult" , key="'playResult'")
	public List<PlayResult> findAllRecords() {
		return dao.findAllRecords();
	}

	@Override
	@Cacheable(value="playResult" , key="#userId")
	public List<PlayResult> findAllRecordsByUserId(int userId) {
		return dao.findAllRecordsByUserId(userId);
	}

	@Override
	public List<PlayResult> findAllRecordsByParams(Map params) {
		// TODO Auto-generated method stub
		return dao.findAllRecordsByParams(params);
	}

	@Override
	public void updateByResult(List<PlayResult> result) {
		
		 dao.updateByResult(result);
	}

	@Override
	public void savePlayResult(PlayResult result) {
		dao.savePlayResult(result);

	}

	@Override
	public void savePlayResultByUserId(PlayResult result, String id) {
		dao.savePlayResultByUserId(result, id);

	}

	@Override
	public void savePlayResultByParam(PlayResult result, Map params) {
		dao.savePlayResultByParam(result, params);
	}

	@Override
	public void updateResult(PlayResult result) {
		dao.updateResult(result);
		
	}

	@Override
	public double getTotalInvestedPointsByUserId(int userId) {
		return dao.getTotalInvestedPointsByUserId(userId);
	}

	@Override
	public double getTotalEarnedPointsByUserId(int userId) {
		return dao.getTotalEarnedPointsByUserId(userId);
	}

	@Override
	public int getTotalWins(int userId) {
		return dao.getTotalWins(userId);
	}

	@Override
	public int getTotalLoss(int userId) {
		return dao.getTotalLoss(userId);
	}

	@Override
	public int getTotalRulewWins(int ruleId, int userId) {
		return dao.getTotalRulewWins(ruleId, userId);
	}

	@Override
	public void removePlayByIds(Map params) {
		dao.removePlayByIds(params);
		
	}
	
}
