package com.backend.dao;

import com.backend.model.MatchResult;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("matchResultDao")
public class MatchResultDaoImpl extends AbstractDao implements MatchResultDao {
    @Autowired
    private EntityManager entityManager;
	
	/*private Criteria createAlias(Criteria criteria ){
		criteria.createAlias("ruleId", "ruleId", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("matchId", "ruleId", JoinType.LEFT_OUTER_JOIN);
		return criteria;
	}*/

    private Selection<MatchResult> createAlias(CriteriaQuery<MatchResult> criteriaQuery){
        return criteriaQuery.from(MatchResult.class).alias("matchId").alias("ruleId");
    }

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

        try{
            CriteriaQuery<MatchResult> criteriaQuery = getSession().getCriteriaBuilder().createQuery(MatchResult.class);
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            Root<MatchResult> root = criteriaQuery.from(MatchResult.class);
            criteriaQuery.where(cb.equal(root.get("matchId").get("id"),matchId));
            return Optional.ofNullable(getSession().createQuery(criteriaQuery).getResultList()).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MatchResult> findAllRecordsByRuleId(int ruleId) {

        TypedQuery<MatchResult> query
                = entityManager.createQuery(
                "SELECT r FROM MatchResult mr, Match m WHERE mr.matchId = matchId", MatchResult.class);
        List<MatchResult> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public void saveMatchResult(MatchResult result) {
        persist(result);

    }

    @Override
    public MatchResult findAllRecordsByRuleIdnadMatchId(int ruleId, int matchId) {
        EntityManager manager = null;
        try{
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<MatchResult> criteriaQuery = getSession().getCriteriaBuilder().createQuery(MatchResult.class);
            Root<MatchResult> root = (Root<MatchResult>) createAlias(criteriaQuery);
            criteriaQuery.where(cb.equal(root.get("matchId").get("id"),matchId), cb.equal(root.get("ruleId").get("id"),ruleId));
            return Optional.ofNullable(getSession().createQuery(criteriaQuery).getSingleResult()).get();
        }
        catch (NoResultException e){
            e.getCause();
        }
       return null;
    }

    @Override
    public void updateMatchResult(MatchResult result) {
        getSession().update(result);

    }


}
