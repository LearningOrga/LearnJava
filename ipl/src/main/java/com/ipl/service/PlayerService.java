package com.ipl.service;

import java.util.List;

import com.ipl.model.Player;

public interface PlayerService {

	void savePlayer(Player player);
	
	List<Player> findAllPlayers();
			
	Player findPlayerById(int playerId);
	
	
}
