package com.backend.dao;

import com.backend.model.MatchResult;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("matchResultDao")
public class MatchResultDaoImpl extends AbstractDao implements MatchResultDao {
    @Autowired
    private EntityManager entityManager;
	
	/*private Criteria createAlias(Criteria criteria ){
		criteria.createAlias("ruleId", "ruleId", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("matchId", "matchId", JoinType.LEFT_OUTER_JOIN);
		return criteria;
	}*/

    @Override
    public List<MatchResult> findAllRecords() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MatchResult> cq = cb.createQuery(MatchResult.class);
        Root<MatchResult> match = cq.from(MatchResult.class);
        cq.select(match);
        return (List<MatchResult>) entityManager.createQuery(cq).getResultList();


    }

    @Override
    public List<MatchResult> findAllRecordsByMatchId(int matchId) {

        TypedQuery<MatchResult> query
                = entityManager.createQuery(
                "SELECT r FROM MatchResult mr, Match m WHERE mr.matchId = matchId", MatchResult.class);
        List<MatchResult> resultList = query.getResultList();
        return resultList;
		/*Criteria criteria = getSession().createCriteria(MatchResult.class);
		criteria=createAlias(criteria);
		criteria.add(Restrictions.eq("matchId.id",matchId));
		return (List<MatchResult>) criteria.list();*/
    }

    @Override
    public List<MatchResult> findAllRecordsByRuleId(int ruleId) {

        TypedQuery<MatchResult> query
                = entityManager.createQuery(
                "SELECT r FROM MatchResult mr, Match m WHERE mr.matchId = matchId", MatchResult.class);
        List<MatchResult> resultList = query.getResultList();
        return resultList;
		/*Criteria criteria = getSession().createCriteria(MatchResult.class);
		criteria=createAlias(criteria);
		criteria.add(Restrictions.eq("ruleId.id",ruleId));
		return (List<MatchResult>) criteria.list();*/
    }

    @Override
    public void saveMatchResult(MatchResult result) {
        //("Saving match result"+result);
        persist(result);

    }

    @Override
    public MatchResult findAllRecordsByRuleIdnadMatchId(int ruleId, int matchId) {

        TypedQuery<MatchResult> query
                = entityManager.createQuery(
                "SELECT r FROM MatchResult mr, Match m WHERE mr.matchId = matchId", MatchResult.class);
        List<MatchResult> resultList = query.getResultList();
        return resultList.get(0);
		/*Criteria criteria = getSession().createCriteria(MatchResult.class);
		criteria=createAlias(criteria);
		criteria.add(Restrictions.eq("matchId.id",matchId));
		criteria.add(Restrictions.eq("ruleId.id",ruleId));
		return (MatchResult) criteria.uniqueResult();*/
    }

    @Override
    public void updateMatchResult(MatchResult result) {
        getSession().update(result);

    }


}
