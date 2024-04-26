package com.backend.dao;

import com.backend.model.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("playerDao")
public class PlayerDaoImpl extends AbstractDao implements PlayerDao{

	@Autowired
	private EntityManager entityManager;

	@Override
	public void savePlayer(Player player) {
		persist(player);
		
	}



	@Override
	@SuppressWarnings("unchecked")
	public List<Player> findAllPlayers() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Player> cq = cb.createQuery(Player.class);
		Root<Player> player = cq.from(Player.class);
		cq.select(player);
		return (List<Player>) entityManager.createQuery(cq).getResultList();

	}

	@Override	
	public Player findPlayerById(int playerId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Player> cq = cb.createQuery(Player.class);
		Root<Player> player = cq.from(Player.class);
		Predicate idPredicate = cb.equal(player.get("id"),playerId);
		cq.where( idPredicate);
		TypedQuery<Player> query = entityManager.createQuery(cq);
		return (Player) query.getSingleResult();

	}

	
	
}
