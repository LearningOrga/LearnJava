package com.ipl.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ipl.model.PlayResult;
import com.ipl.model.Predict;
import com.ipl.model.Team;

@Repository("predictDao")
public class PredictDaoImpl extends AbstractDao implements PredictDao{

	@Override
	public void savePredict(Predict predict) {
		persist(predict);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Predict> findAllPredicts() {
		//("I am in Dao");
		Criteria criteria = getSession().createCriteria(Predict.class);
		
		//("I am in Dao"+criteria.toString());
		List<Predict> list = criteria.list();
//		List<Predict> finalList = new ArrayList();
//		for (Predict predict : list) {
//			Team team1 = predict.getQfTeam1Result();
//			Team team2 = predict.getQfTeam2Result();
//			Team team3 = predict.getQfTeam3Result();
//			Team team4 = predict.getQfTeam4Result();
//			Team team5;
//			Team team6;
//			Team team7;
//			if(predict.getQfteam1().getId() == predict.getQfTeam1Result().getId() && 
//					predict.getQfteam2().getId() == .getId() && 
//					predict.getQfteam3().getId() == predict.getQfTeam3Result().getId() && 
//					predict.getQfteam4().getId() == predict.getQfTeam4Result().getId() && 
//					predict.getSfteam1().getId() == predict.getSfTeam1Result().getId() && 
//					predict.getSfteam2().getId() == predict.getSfTeam2Result().getId() && 
//					predict.getFinalWinningTeam().getId() == predict.getFinalWinningTeamResult().getId()){
//				
//				finalList.add(predict);
//			}
//		}
		return list;
	}


	@Override
	public Predict findByUserId(int userId){
		//("I am in Dao");
		Criteria criteria = getSession().createCriteria(Predict.class);
		//("I am in Dao"+userId);
		criteria.add(Restrictions.eq("userId.id",userId));
		return (Predict) criteria.uniqueResult();
	}
	@Override
	public void updatePredict(Predict predict){
		getSession().update(predict);
	}
	
}
