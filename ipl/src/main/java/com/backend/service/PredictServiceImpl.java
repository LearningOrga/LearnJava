package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dao.PredictDao;
import com.backend.model.Predict;

@Service("predictService")
@Transactional
@CacheConfig(cacheNames = "predict")
public class PredictServiceImpl implements PredictService{

	@Autowired
	private PredictDao dao;
	@Override
	public void savePredict(Predict predict) {
		dao.savePredict(predict);
	}
	@Override
	@Cacheable(value="predict" , key="'predict'")
	public List<Predict> findAllPredicts() {
		return dao.findAllPredicts();
	}

	
	@Cacheable(value="predict" , key="#userId")
	public Predict findByUserId(int userId) {
		return dao.findByUserId(userId);
	}

	public void updatePredict(Predict predict){
		dao.updatePredict(predict);
	}
}
