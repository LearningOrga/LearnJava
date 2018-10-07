package com.ipl.service;

import java.util.List;

import com.ipl.model.Predict;

public interface PredictService {

	void savePredict(Predict predict);

	List<Predict> findAllPredicts();

	
	Predict findByUserId(int userId);

	void updatePredict(Predict predict);
}
