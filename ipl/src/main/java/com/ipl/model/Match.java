package com.ipl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="match_master")

public class Match implements Serializable {
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 6054969663026191913L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MATCH_NO", nullable = false)
	private int id;
	
	
	@Column(name = "MATCH_DATE", nullable = false)	
	private String matchDate;
	
	@Column(name = "MATCH_DAY", nullable = false)
	private String matchDay;
	
	
	@Column(name = "MATCH_TIME", nullable = false)	
	private String matchTime;
	
	
	@Column(name = "MATCH_DETAILS", nullable = false)
	private String matchDetails;
	
	
	@Column(name = "MATCH_VENUE", nullable = false)
	private String matchVenue;
	
	@Column(name = "MATCH_STATUS")
	private String matchStatus;

public Match(){}

public Match(int id) {
	super();
	this.id = id;
}

	public Match(String matchDate, String matchDay, String matchTime,
			String matchDetails, String matchVenue,String matchStatus) {
		super();
		this.matchDate = matchDate;
		this.matchDay = matchDay;
		this.matchTime = matchTime;
		this.matchDetails = matchDetails;
		this.matchVenue = matchVenue;
		this.matchStatus = matchStatus;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMatchDate() {
		return matchDate;
	}


	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}


	public String getMatchDay() {
		return matchDay;
	}


	public void setMatchDay(String matchDay) {
		this.matchDay = matchDay;
	}


	public String getMatchTime() {
		return matchTime;
	}


	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}


	public String getMatchDetails() {
		return matchDetails;
	}


	public void setMatchDetails(String matchDetails) {
		this.matchDetails = matchDetails;
	}


	public String getMatchVenue() {
		return matchVenue;
	}


	public void setMatchVenue(String matchVenue) {
		this.matchVenue = matchVenue;
	}

	public String getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(String matchStatus) {
		this.matchStatus = matchStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((matchTime == null) ? 0 : matchTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Match))
			return false;
		Match other = (Match) obj;
		if (id != other.id)
			return false;
		if (matchTime == null) {
			if (other.matchTime != null)
				return false;
		} else if (!matchTime.equals(other.matchTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MatchMaster [id=" + id + ", matchDate=" + matchDate
				+ ", matchDay=" + matchDay + ", matchTime=" + matchTime
				+ ", matchDetails=" + matchDetails + ", matchVenue="
				+ matchVenue + "]";
	}
	
	
}
