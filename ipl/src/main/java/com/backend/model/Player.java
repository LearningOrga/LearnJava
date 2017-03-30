package com.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name="player_master")
public class Player implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -730009276915780426L;


	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLAYER_ID", nullable = false)
	private int id;
	
	
	@Column(name = "PLAYER_NAME", nullable = false)	
	private String playerName;
	
	@Column(name = "PLAYER_TYPE")
	private String playerType;
	
	
	
	 @ManyToOne	 
	 @JoinColumn(name = "PLAYER_TEAM",nullable = false)	 
	 private Team playerTeam;
	
	
	@Column(name = "PLAYER_STAR_STATUS")	
	private String playerStarStatus;

	public Player(){}

	public Player(String playerName, String playerType, Team playerTeam,
			String playerStarStatus) {
		super();
		this.playerName = playerName;
		this.playerType = playerType;
		this.playerTeam = playerTeam;
		this.playerStarStatus = playerStarStatus;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public String getPlayerType() {
		return playerType;
	}


	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}


	public Team getPlayerTeam() {
		return playerTeam;
	}


	public void setPlayerTeam(Team playerTeam) {
		this.playerTeam = playerTeam;
	}


	public String getPlayerStarStatus() {
		return playerStarStatus;
	}


	public void setPlayerStarStatus(String playerStarStatus) {
		this.playerStarStatus = playerStarStatus;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;		
		result = prime * result + this.id;
		result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Player))
			return false;
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		if (playerName == null) {
			if (other.playerName != null)
				return false;
		} else if (!playerName.equals(other.playerName))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Player [id=" + id + ", playerName=" + playerName
				+ ", playerType=" + playerType + ", playerTeam=" + playerTeam
				+ ", playerStarStatus=" + playerStarStatus + "]";
	}
	
	
}
