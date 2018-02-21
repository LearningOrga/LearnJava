package com.ipl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ipl.model.Player;

@Repository("playerDao")
public class PlayerDaoImpl extends AbstractDao implements PlayerDao{

	@Override
	public void savePlayer(Player player) {
		persist(player);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Player> findAllPlayers() {
		Criteria criteria = getSession().createCriteria(Player.class);
		//("here");
		return (List<Player>) criteria.list();
	}

	@Override	
	public Player findPlayerById(int playerId) {
		Criteria criteria = getSession().createCriteria(Player.class);
		criteria.add(Restrictions.eq("id",playerId));
		return (Player) criteria.uniqueResult();
	}

	
	
}
