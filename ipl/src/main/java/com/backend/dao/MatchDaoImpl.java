package com.backend.dao;

import com.backend.model.Match;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("matchMasterDao")

public class MatchDaoImpl extends AbstractDao implements MatchDao{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntityManager entityManager;

	@Override
	public void saveMatch(Match match) {
		persist(match);
		
	}

	@Override
	@SuppressWarnings("unchecked")

	public List<Match> findAllMatches() {
		logger.info("Calling db...");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Match> cq = cb.createQuery(Match.class);
		Root<Match> match = cq.from(Match.class);
		cq.select(match);
		return (List<Match>) entityManager.createQuery(cq).getResultList();
	}

	@Override
	
	public Match findMatchById(int matchId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Match> cq = cb.createQuery(Match.class);
		Root<Match> match = cq.from(Match.class);
		Predicate idPredicate = cb.equal(match.get("id"),matchId);
		cq.where( idPredicate);
		TypedQuery<Match> query = entityManager.createQuery(cq);
		return (Match) query.getSingleResult();
	}

	@Override
	public void updateMatch(Match match) {
		getSession().update(match);
		
	}

	@Override
	public void removeMatchById(int matchId) {
		getSession().delete(findMatchById(matchId));
		
	}

	
	
}
