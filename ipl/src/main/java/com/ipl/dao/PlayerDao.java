package com.ipl.dao;

import java.util.List;

import com.ipl.model.Player;

public interface PlayerDao {

	void savePlayer(Player player);
	
	List<Player> findAllPlayers();
			
	Player findPlayerById(int playerId);
	
	
}
