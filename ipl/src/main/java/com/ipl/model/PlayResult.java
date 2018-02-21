package com.ipl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Table(name="play_result")
public class PlayResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 847559105615532122L;

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "PLAY_MASTER_ID", nullable = false)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="LOGIN_ID", nullable = false)
	private User userId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="RULE_ID", nullable = false)
	 
	private Rule ruleId;
	
	@Column(name = "RULE_VALUE", nullable = false)
	private String ruleValue;
	
	@Column(name = "RULE_RESULT", nullable = false)
	private String ruleResult;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="MATCH_ID", nullable = false)
	
	private Match matchId;
	
	
	@Column(name = "POINTS_INVESTED", nullable = false)
	
	private Double pointsInvested;
	
	@Column(name = "RESULT")
	private String result;
	
	
	@Column(name = "INDICATIVE_POINTS_EARNED")
	private Double indicativePoints;
	
	@Column(name = "TOTAL_POINTS_EARNED")
	private Double totalPointsEarned;

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

	public Rule getRuleId() {
		return ruleId;
	}

	public void setRuleId(Rule ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleValue() {
		return ruleValue;
	}

	public void setRuleValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}

	public Match getMatchId() {
		return matchId;
	}

	public void setMatchId(Match matchId) {
		this.matchId = matchId;
	}

	
	public Double getPointsInvested() {
		return pointsInvested;
	}
	/*TODO
	@PrePersist  
	@PreUpdate
	public void setPointsInvested(Double pointsInvested) {
		this.pointsInvested = pointsInvested;
		if( Double.isInfinite(this.pointsInvested))
			this.pointsInvested = new Double("0");
	}*/
	
	public void setPointsInvested(Double pointsInvested) {
		this.pointsInvested = pointsInvested;
		if( Double.isInfinite(this.pointsInvested))
			this.pointsInvested = new Double("0");
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	
	public Double getIndicativePoints() {
		return indicativePoints;
	}
	/*TODO
	@PrePersist  
	@PreUpdate
	public void setIndicativePoints(Double indicativePoints) {
		this.indicativePoints = indicativePoints;
		if( Double.isInfinite(this.indicativePoints))
			this.indicativePoints = new Double("0");
	}*/
	
	public void setIndicativePoints(Double indicativePoints) {
		this.indicativePoints = indicativePoints;
		if( Double.isInfinite(this.indicativePoints))
			this.indicativePoints = new Double("0");
	}

	public Double getTotalPointsEarned() {
		return totalPointsEarned;
	}
	
	/*TODO
	@PrePersist  
	@PreUpdate
	public void setTotalPointsEarned(Double totalPointsEarned) {
		this.totalPointsEarned = totalPointsEarned;
		if( Double.isInfinite(this.totalPointsEarned))
			this.totalPointsEarned = new Double("0");
	}*/
	
	
	public void setTotalPointsEarned(Double totalPointsEarned) {
		this.totalPointsEarned = totalPointsEarned;
		if( Double.isInfinite(this.totalPointsEarned))
			this.totalPointsEarned = new Double("0");
	}
	
	
	public String getRuleResult() {
		return ruleResult;
	}

	public void setRuleResult(String ruleResult) {
		this.ruleResult = ruleResult;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((totalPointsEarned == null) ? 0 : totalPointsEarned.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PlayResult))
			return false;
		PlayResult other = (PlayResult) obj;
		if (id != other.id)
			return false;
		if (totalPointsEarned == null) {
			if (other.totalPointsEarned != null)
				return false;
		} else if (!totalPointsEarned.equals(other.totalPointsEarned))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlayResult [id=" + id + ", userId=" + userId + ", ruleId="
				+ ruleId + ", ruleValue=" + ruleValue + ", ruleResult="
				+ ruleResult + ", matchId=" + matchId + ", pointsInvested="
				+ pointsInvested + ", result=" + result + ", indicativePoints="
				+ indicativePoints + ", totalPointsEarned=" + totalPointsEarned
				+ "]";
	}

	
	
}
