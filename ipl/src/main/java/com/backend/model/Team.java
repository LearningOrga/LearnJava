package com.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="team_master")
public class Team implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1637292535699347414L;


	@Id	
	@Column(name = "TEAM_ID", nullable = false)
	private int id;
	
	
	@Column(name = "TEAM_NAME", nullable = false)	
	private String teamName;
	
	@Column(name = "TEAM_HOME_VENUE", nullable = false)
	private String teamHomeVenue;
	
	@Column(name = "TEAM_CAPTAIN", nullable = false)
	private String teamCaptain;
	
	
	
	public Team(){}
	
	public Team(int id) {
		super();
		this.id = id;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamHomeVenue() {
		return teamHomeVenue;
	}

	public void setTeamHomeVenue(String teamHomeVenue) {
		this.teamHomeVenue = teamHomeVenue;
	}

	public String getTeamCaptain() {
		return teamCaptain;
	}

	public void setTeamCaptain(String teamCaptain) {
		this.teamCaptain = teamCaptain;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;		
		result = prime * result + this.id;
		result = prime * result + ((teamName == null) ? 0 : teamName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Team))
			return false;
		Team other = (Team) obj;
		if (id != other.id)
			return false;
		if (teamName == null) {
			if (other.teamName != null)
				return false;
		} else if (!teamName.equals(other.teamName))
			return false;
		return true;
	}

	public String toString() {
		return "Player [id=" + id + ", teamName=" + teamName
				+ ", teamHomeVenue=" + teamHomeVenue + ", teamCaptain="
				+ teamCaptain + "]";
	}


	
	
	
}
