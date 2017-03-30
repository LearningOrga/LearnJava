package com.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name="rule_master")
public class Rule implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4033282747353741985L;


	@Id	
	@Column(name = "RULE_ID", nullable = false)
	private int id;
	
	
	@Column(name = "RULE_NAME", nullable = false)	
	private String ruleName;
	
	@Column(name = "RULE_DESC", nullable = false)
	private String ruleDesc;
	
	@Column(name = "RULE_STATUS", nullable = false)
	private String ruleStatus;
	
	
	@Column(name = "RULE_BASED_ON", nullable = false)
	private String ruleBasedOn;
	

	public Rule(){}
	
	public Rule(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleDesc() {
		return ruleDesc;
	}

	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}

	public String getRuleStatus() {
		return ruleStatus;
	}

	public void setRuleStatus(String ruleStatus) {
		this.ruleStatus = ruleStatus;
	}

	public String getRuleBasedOn() {
		return ruleBasedOn;
	}

	public void setRuleBasedOn(String ruleBasedOn) {
		this.ruleBasedOn = ruleBasedOn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;		
		result = prime * result + this.id;
		result = prime * result + ((ruleName == null) ? 0 : ruleName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Rule))
			return false;
		Rule other = (Rule) obj;
		if (id != other.id)
			return false;
		if (ruleName == null) {
			if (other.ruleName != null)
				return false;
		} else if (!ruleName.equals(other.ruleName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rule [id=" + id + ", ruleName=" + ruleName + ", ruleDesc="
				+ ruleDesc + ", ruleStatus=" + ruleStatus + ", ruleBasedOn="
				+ ruleBasedOn + "]";
	}

	


	
	
	
}
