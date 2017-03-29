package com.ipl.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Player;
import com.backend.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	PlayerService playerService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody
	List<Player> getAllPlayers(ModelMap model) {

		List<Player> players = playerService.findAllPlayers();

		return players;
	}

	@RequestMapping(value = "/{playerId}", method = RequestMethod.GET)
	public @ResponseBody
	Player getPlayerById(ModelMap model, @PathVariable("playerId") int playerId) {

		Player player = playerService.findPlayerById(playerId);

		return player;
	}
}