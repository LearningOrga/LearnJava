package com.backend.dao;

import com.backend.model.PlayResult;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("playResultDao")
public class PlayResultDaoImpl extends AbstractDao implements PlayResultDao {

	@Autowired
	private EntityManager entityManager;

	/*private Criteria createAlias(Criteria criteria) {
		criteria.createAlias("userId", "userId", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("ruleId", "ruleId", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("matchId", "matchId", JoinType.LEFT_OUTER_JOIN);
		return criteria;
	}*/

	@Override
	public List<PlayResult> findAllRecords() {
  		//TODO:order
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlayResult> cq = cb.createQuery(PlayResult.class);
		Root<PlayResult> match = cq.from(PlayResult.class);
		cq.select(match);
		return (List<PlayResult>) entityManager.createQuery(cq).getResultList();
		/*Criteria criteria = getSession().createCriteria(PlayResult.class);
		criteria.addOrder(Order.desc("totalPointsEarned"));
		return (List<PlayResult>) criteria.list();*/
	}

	@Override
	public List<PlayResult> findAllRecordsByUserId(int userId) {

		//TODO
		TypedQuery<PlayResult> query
				= entityManager.createQuery(
				"SELECT r FROM MatchResult mr, Match m WHERE mr.matchId = matchId", PlayResult.class);
		List<PlayResult> resultList = query.getResultList();
		return resultList;
		/*Criteria criteria = getSession().createCriteria(PlayResult.class);
		criteria = createAlias(criteria);
		criteria.add(Restrictions.eq("userId.id", userId));
		return (List<PlayResult>) criteria.list();*/
	}

	@Override
	public List<PlayResult> findAllRecordsByParams(Map params) {
		//TODO
		TypedQuery<PlayResult> query
				= entityManager.createQuery(
				"SELECT r FROM MatchResult mr, Match m WHERE mr.matchId = matchId", PlayResult.class);
		List<PlayResult> resultList = query.getResultList();
		return resultList;
		/*Criteria criteria = getSession().createCriteria(PlayResult.class);
		criteria.addOrder(Order.desc("totalPointsEarned"));
		criteria = createAlias(criteria);
		if (params.containsKey("ruleId")) {
			criteria.add(Restrictions.eq("ruleId.id", params.get("ruleId")));
		}
		if (params.containsKey("matchId")) {
			criteria.add(Restrictions.eq("matchId.id", params.get("matchId")));
		}
		if (params.containsKey("userId")) {
			criteria.add(Restrictions.eq("userId.id", params.get("userId")));
		}
		// ("critera--------------" + criteria);
		return (List<PlayResult>) criteria.list();*/
	}

	@Override
	public void updateByResult(List<PlayResult> results) {
		for (PlayResult result : results) {
			getSession().update(result);
		}

	}

	@Override
	public void savePlayResult(PlayResult result) {
		// ("Saving result" + result);
		persist(result);
	}

	@Override
	public void savePlayResultByUserId(PlayResult result, String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void savePlayResultByParam(PlayResult result, Map params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateResult(PlayResult result) {
		getSession().update(result);

	}

	@Override
	public double getTotalInvestedPointsByUserId(int userId) {
		List<PlayResult> results = findAllRecordsByUserId(userId);

		double retVal = 0;
		for (PlayResult result : results) {
			if (result.getPointsInvested() != null)
				retVal = retVal + result.getPointsInvested();
		}
		return retVal;
	}

	@Override
	public double getTotalEarnedPointsByUserId(int userId) {
		List<PlayResult> results = findAllRecordsByUserId(userId);

		double retVal = 0;
		for (PlayResult result : results) {
			if (result.getTotalPointsEarned() != null)
				retVal = retVal + result.getTotalPointsEarned();
		}
		return retVal;
	}

	@Override
	public int getTotalWins(int userId) {
		List<PlayResult> results = findAllRecordsByUserId(userId);

		int retVal = 0;
		for (PlayResult result : results) {
			if (result.getResult() != null) {
				if (result.getResult().equals("WIN"))
					retVal++;
			}
		}
		return retVal;
	}

	@Override
	public int getTotalLoss(int userId) {
		List<PlayResult> results = findAllRecordsByUserId(userId);

		int retVal = 0;
		for (PlayResult result : results) {
			if (result.getResult() != null) {
				if (result.getResult().equals("LOSS"))
					retVal++;
			}
		}
		return retVal;
	}

	@Override
	public int getTotalRulewWins(int ruleId, int userId) {
		List<PlayResult> results = findAllRecordsByUserId(userId);
		int retVal = 0;
		for (PlayResult result : results) {
			if (result.getRuleId().getId() == ruleId) {
				if (result.getResult() != null
						&& result.getResult().equals("WIN")) {
					retVal = retVal + 1;
				}
			}

		}
		return retVal;
	}

	@Override
	public void removePlayByIds(Map params) {

		//TODO
		TypedQuery<PlayResult> query
				= entityManager.createQuery(
				"SELECT r FROM MatchResult mr, Match m WHERE mr.matchId = matchId", PlayResult.class);
		List<PlayResult> resultList = query.getResultList();

		/*
		Criteria criteria = getSession().createCriteria(PlayResult.class);
		criteria = createAlias(criteria);
		if (params.containsKey("ruleId")) {
			criteria.add(Restrictions.eq("ruleId.id", params.get("ruleId")));
		}
		if (params.containsKey("matchId")) {
			criteria.add(Restrictions.eq("matchId.id", params.get("matchId")));
		}
		if (params.containsKey("userId")) {
			criteria.add(Restrictions.eq("userId.id", params.get("userId")));
		}
		// ("critera--------------" + criteria);

		List<PlayResult> list = criteria.list();

		for (int i = 1; i < list.size(); i++) {

			getSession().delete(list.get(i));
		}
		// return getSession().delete(list);*/

	}

	

}
