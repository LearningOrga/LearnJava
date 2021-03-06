package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dao.PlayerDao;
import com.backend.model.Player;

@Service("playerService")
@Transactional
@CacheConfig(cacheNames = "player")
public class PlayerServiceImpl  implements PlayerService{

	@Autowired
	private PlayerDao playerDao;
	
	@Override
	public void savePlayer(Player player) {
		playerDao.savePlayer(player);
		
	}

	@Override	
	public List<Player> findAllPlayers() {
	return	playerDao.findAllPlayers();
	}

	@Override	
	public Player findPlayerById(int playerId) {
		return playerDao.findPlayerById(playerId);
	}

	
	
}
