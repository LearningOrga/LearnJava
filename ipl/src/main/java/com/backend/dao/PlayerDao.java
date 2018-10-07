package com.backend.dao;

import java.util.List;

import com.backend.model.Player;

public interface PlayerDao {

	void savePlayer(Player player);
	
	List<Player> findAllPlayers();
			
	Player findPlayerById(int playerId);
	
	
}
