package com.backend.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.backend.model.MatchResult;

@Repository("matchResultDao")
public class MatchResultDaoImpl extends AbstractDao implements MatchResultDao {

	
	private Criteria createAlias(Criteria criteria ){		
		criteria.createAlias("ruleId", "ruleId", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("matchId", "matchId", JoinType.LEFT_OUTER_JOIN);
		return criteria;
	}

	@Override
	public List<MatchResult> findAllRecords() {
		Criteria criteria = getSession().createCriteria(MatchResult.class);
		return (List<MatchResult>) criteria.list();
	}

	@Override
	public List<MatchResult> findAllRecordsByMatchId(int matchId) {
		Criteria criteria = getSession().createCriteria(MatchResult.class);
		criteria=createAlias(criteria);
		criteria.add(Restrictions.eq("matchId.id",matchId));
		return (List<MatchResult>) criteria.list();
	}

	@Override
	public List<MatchResult> findAllRecordsByRuleId(int ruleId) {
		Criteria criteria = getSession().createCriteria(MatchResult.class);
		criteria=createAlias(criteria);
		criteria.add(Restrictions.eq("ruleId.id",ruleId));
		return (List<MatchResult>) criteria.list();
	}

	@Override
	public void saveMatchResult(MatchResult result) {
		//("Saving match result"+result);
		persist(result);
		
	}

	@Override
	public MatchResult findAllRecordsByRuleIdnadMatchId(int ruleId, int matchId) {
		Criteria criteria = getSession().createCriteria(MatchResult.class);
		criteria=createAlias(criteria);
		//("matchId.id"+matchId);
		//("ruleId.id"+ruleId);
		criteria.add(Restrictions.eq("matchId.id",matchId));
		criteria.add(Restrictions.eq("ruleId.id",ruleId));
		return (MatchResult) criteria.uniqueResult();
		}

	@Override
	public void updateMatchResult(MatchResult result) {
		getSession().update(result);
		
	}

	

}
