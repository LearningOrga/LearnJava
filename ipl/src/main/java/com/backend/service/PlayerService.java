package com.backend.service;

import java.util.List;

import com.backend.model.Player;

public interface PlayerService {

	void savePlayer(Player player);
	
	List<Player> findAllPlayers();
			
	Player findPlayerById(int playerId);
	
	
}
