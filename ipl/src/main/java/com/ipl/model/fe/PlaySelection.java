package com.ipl.model.fe;

import java.io.Serializable;

public class PlaySelection implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int ruleId = 0;
	String selTeamName = "";
	int matchId = 0;
	String pointsInvested = "";
	String selMatchTime = "";
	String ruleValue="";
	
	

	public String getRuleValue() {
		return ruleValue;
	}

	public void setRuleValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}

	public PlaySelection() {
	
	}

	public PlaySelection(int ruleId, String selTeamName, int matchId,
			String pointsInvested, String selMatchTime,String ruleValue) {
		super();
		this.ruleId = ruleId;
		this.selTeamName = selTeamName;
		this.matchId = matchId;
		this.pointsInvested = pointsInvested;
		this.selMatchTime = selMatchTime;
		this.ruleValue=ruleValue;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public String getSelTeamName() {
		return selTeamName;
	}

	public void setSelTeamName(String selTeamName) {
		this.selTeamName = selTeamName;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public String getPointsInvested() {
		return pointsInvested;
	}

	public void setPointsInvested(String pointsInvested) {
		this.pointsInvested = pointsInvested;
	}

	public String getSelMatchTime() {
		return selMatchTime;
	}

	public void setSelMatchTime(String selMatchTime) {
		this.selMatchTime = selMatchTime;
	}

	@Override
	public String toString() {
		return "PlaySelection [ruleId=" + ruleId + ", selTeamName="
				+ selTeamName + ", matchId=" + matchId + ", pointsInvested="
				+ pointsInvested + ", selMatchTime=" + selMatchTime
				+ ", ruleValue=" + ruleValue + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + matchId;
		result = prime * result
				+ ((pointsInvested == null) ? 0 : pointsInvested.hashCode());
		result = prime * result + ruleId;
		result = prime * result
				+ ((ruleValue == null) ? 0 : ruleValue.hashCode());
		result = prime * result
				+ ((selMatchTime == null) ? 0 : selMatchTime.hashCode());
		result = prime * result
				+ ((selTeamName == null) ? 0 : selTeamName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlaySelection other = (PlaySelection) obj;
		if (matchId != other.matchId)
			return false;
		if (pointsInvested == null) {
			if (other.pointsInvested != null)
				return false;
		} else if (!pointsInvested.equals(other.pointsInvested))
			return false;
		if (ruleId != other.ruleId)
			return false;
		if (ruleValue == null) {
			if (other.ruleValue != null)
				return false;
		} else if (!ruleValue.equals(other.ruleValue))
			return false;
		if (selMatchTime == null) {
			if (other.selMatchTime != null)
				return false;
		} else if (!selMatchTime.equals(other.selMatchTime))
			return false;
		if (selTeamName == null) {
			if (other.selTeamName != null)
				return false;
		} else if (!selTeamName.equals(other.selTeamName))
			return false;
		return true;
	}

	

	

}
