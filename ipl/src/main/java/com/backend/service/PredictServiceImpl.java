package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dao.PredictDao;
import com.backend.model.Predict;

@Service("predictService")
@Transactional
public class PredictServiceImpl implements PredictService{

	@Autowired
	private PredictDao dao;
	@Override
	public void savePredict(Predict predict) {
		dao.savePredict(predict);
	}
	@Override
	public List<Predict> findAllPredicts() {
		return dao.findAllPredicts();
	}

	

	public Predict findByUserId(int userId) {
		return dao.findByUserId(userId);
	}

	public void updatePredict(Predict predict){
		dao.updatePredict(predict);
	}
}
