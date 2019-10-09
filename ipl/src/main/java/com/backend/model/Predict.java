package com.backend.model;

import org.hibernate.envers.NotAudited;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="predict_master")
public class Predict implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4411068597690338377L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PREDICT_ID", nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LOGIN_ID",updatable = false, insertable = false, nullable = false)
	@NotAudited
	private User userId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEAM_ID",updatable = false, insertable = false, nullable = false)
	@NotAudited
	private Team qfteam1;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEAM_ID",updatable = false, insertable = false, nullable = false)
	@NotAudited
	private Team qfteam2;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEAM_ID",updatable = false, insertable = false, nullable = false)
	@NotAudited
	private Team qfteam3;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEAM_ID",updatable = false, insertable = false, nullable = false)
	@NotAudited
	private Team qfteam4;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEAM_ID",updatable = false, insertable = false, nullable = false)
	@NotAudited
	private Team sfteam1;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEAM_ID",updatable = false, insertable = false, nullable = false)
	@NotAudited
	private Team sfteam2;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEAM_ID",updatable = false, insertable = false, nullable = false)
	@NotAudited
	private Team finalWinningTeam;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEAM_ID",updatable = false, insertable = false, nullable = false)
	@NotAudited
	private Team qfTeam1Result;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEAM_ID",updatable = false, insertable = false, nullable = false)
	@NotAudited
	private Team qfTeam2Result;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEAM_ID",updatable = false, insertable = false, nullable = false)
	@NotAudited
	private Team qfTeam3Result;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEAM_ID",updatable = false, insertable = false, nullable = false)
	@NotAudited
	private Team qfTeam4Result;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEAM_ID",updatable = false, insertable = false, nullable = false)
	@NotAudited
	private Team sfTeam1Result;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEAM_ID",updatable = false, insertable = false, nullable = false)
	@NotAudited
	private Team sfTeam2Result;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEAM_ID" , updatable = false, insertable = false, nullable = false)
	@NotAudited
	private Team finalWinningTeamResult;

	@Column(name = "USER_PROB_INDEX")
	private int userProbIndex;

	public Predict(){}
	
	public Predict(User userId, Team qfteam1, Team qfteam2, Team qfteam3,
			Team qfteam4, Team sfteam1, Team sfteam2, Team finalWinningTeam
			) {
		super();
		this.userId = userId;
		this.qfteam1 = qfteam1;
		this.qfteam2 = qfteam2;
		this.qfteam3 = qfteam3;
		this.qfteam4 = qfteam4;
		this.sfteam1 = sfteam1;
		this.sfteam2 = sfteam2;
		this.finalWinningTeam = finalWinningTeam;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Team getQfteam1() {
		return qfteam1;
	}

	public void setQfteam1(Team qfteam1) {
		this.qfteam1 = qfteam1;
	}

	public Team getQfteam2() {
		return qfteam2;
	}

	public void setQfteam2(Team qfteam2) {
		this.qfteam2 = qfteam2;
	}

	public Team getQfteam3() {
		return qfteam3;
	}

	public void setQfteam3(Team qfteam3) {
		this.qfteam3 = qfteam3;
	}

	public Team getQfteam4() {
		return qfteam4;
	}

	public void setQfteam4(Team qfteam4) {
		this.qfteam4 = qfteam4;
	}

	public Team getSfteam1() {
		return sfteam1;
	}

	public void setSfteam1(Team sfteam1) {
		this.sfteam1 = sfteam1;
	}

	public Team getSfteam2() {
		return sfteam2;
	}

	public void setSfteam2(Team sfteam2) {
		this.sfteam2 = sfteam2;
	}

	public Team getFinalWinningTeam() {
		return finalWinningTeam;
	}

	public void setFinalWinningTeam(Team finalWinningTeam) {
		this.finalWinningTeam = finalWinningTeam;
	}

	public Team getQfTeam1Result() {
		return qfTeam1Result;
	}

	public void setQfTeam1Result(Team qfTeam1Result) {
		this.qfTeam1Result = qfTeam1Result;
	}

	public Team getQfTeam2Result() {
		return qfTeam2Result;
	}

	public void setQfTeam2Result(Team qfTeam2Result) {
		this.qfTeam2Result = qfTeam2Result;
	}

	public Team getQfTeam3Result() {
		return qfTeam3Result;
	}

	public void setQfTeam3Result(Team qfTeam3Result) {
		this.qfTeam3Result = qfTeam3Result;
	}

	public Team getQfTeam4Result() {
		return qfTeam4Result;
	}

	public void setQfTeam4Result(Team qfTeam4Result) {
		this.qfTeam4Result = qfTeam4Result;
	}

	public Team getSfTeam1Result() {
		return sfTeam1Result;
	}

	public void setSfTeam1Result(Team sfTeam1Result) {
		this.sfTeam1Result = sfTeam1Result;
	}

	public Team getSfTeam2Result() {
		return sfTeam2Result;
	}

	public void setSfTeam2Result(Team sfTeam2Result) {
		this.sfTeam2Result = sfTeam2Result;
	}

	public Team getFinalWinningTeamResult() {
		return finalWinningTeamResult;
	}

	public void setFinalWinningTeamResult(Team finalWinningTeamResult) {
		this.finalWinningTeamResult = finalWinningTeamResult;
	}

	public int getUserProbIndex() {
		return userProbIndex;
	}

	public void setUserProbIndex(int userProbIndex) {
		this.userProbIndex = userProbIndex;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Predict))
			return false;
		Predict other = (Predict) obj;
		if (id != other.id)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Predict [id=" + id + ", userId=" + userId + ", qfteam1="
				+ qfteam1 + ", qfteam2=" + qfteam2 + ", qfteam3=" + qfteam3
				+ ", qfteam4=" + qfteam4 + ", sfteam1=" + sfteam1
				+ ", sfteam2=" + sfteam2 + ", finalWinningTeam="
				+ finalWinningTeam + ", qfTeam1Result=" + qfTeam1Result
				+ ", qfTeam2Result=" + qfTeam2Result + ", qfTeam3Result="
				+ qfTeam3Result + ", qfTeam4Result=" + qfTeam4Result
				+ ", sfTeam1Result=" + sfTeam1Result + ", sfTeam2Result="
				+ sfTeam2Result + ", finalWinningTeamResult="
				+ finalWinningTeamResult + ", userProbIndex=" + userProbIndex
				+ "]";
	}

	

}
