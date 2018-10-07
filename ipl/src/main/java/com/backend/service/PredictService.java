package com.backend.service;

import java.util.List;

import com.backend.model.Predict;

public interface PredictService {

	void savePredict(Predict predict);

	List<Predict> findAllPredicts();

	
	Predict findByUserId(int userId);

	void updatePredict(Predict predict);
}
