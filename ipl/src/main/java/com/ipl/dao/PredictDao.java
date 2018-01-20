package com.ipl.dao;

import java.util.List;

import com.ipl.model.Predict;

public interface PredictDao {

	void savePredict(Predict predict);

	List<Predict> findAllPredicts();

	Predict findByUserId(int userId);

	void updatePredict(Predict predict);
}
