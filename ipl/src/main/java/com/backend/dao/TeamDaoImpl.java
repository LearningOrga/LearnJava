package com.backend.dao;

import com.backend.model.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("teamDao")
public class TeamDaoImpl extends AbstractDao implements TeamDao{
	@Autowired
	private EntityManager entityManager;
	@Override
	public void saveTeam(Team team) {
		persist(team);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Team> findAllTeams() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Team> cq = cb.createQuery(Team.class);
		Root<Team> match = cq.from(Team.class);
		cq.select(match);
		return (List<Team>) entityManager.createQuery(cq).getResultList();

	}

	@Override	
	public Team findTeamById(int teamId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Team> cq = cb.createQuery(Team.class);
		Root<Team> match = cq.from(Team.class);
		Predicate idPredicate = cb.equal(match.get("id"),teamId);
		cq.where( idPredicate);
		TypedQuery<Team> query = entityManager.createQuery(cq);
		return (Team) query.getSingleResult();

	}

	
	
}
