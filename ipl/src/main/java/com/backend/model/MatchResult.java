package com.backend.model;

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

import org.hibernate.envers.NotAudited;

@Entity
@Table(name="match_result")
public class MatchResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5082760697766711827L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MATCH_RESULT_ID", nullable = false)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)	
	@JoinColumn(name="MATCH_ID", nullable = false)
	 @NotAudited
	private Match matchId;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="RULE_ID", nullable = false)
	 @NotAudited
	private Rule ruleId;
	
	@Column(name = "RULE_RESULT", nullable = false)
	private String ruleResult;


	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Match getMatchId() {
		return matchId;
	}

	public void setMatchId(Match matchId) {
		this.matchId = matchId;
	}

	public Rule getRuleId() {
		return ruleId;
	}

	public void setRuleId(Rule ruleId) {
		this.ruleId = ruleId;
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
		result = prime * result + ((ruleResult == null) ? 0 : ruleResult.hashCode());
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
		MatchResult other = (MatchResult) obj;
		if (id != other.id)
			return false;
		if (ruleResult == null) {
			if (other.ruleResult != null)
				return false;
		} else if (!ruleResult.equals(other.ruleResult))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MatchResult [id=" + id + ", matchId=" + matchId + ", ruleId="
				+ ruleId + ", ruleResult=" + ruleResult + "]";
	}

	
	
	
}
