package com.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

@Entity
@Table(name="user_master")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -790642046969411800L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LOGIN_ID", nullable = false)
	private int id;
	
	
	@Column(name = "LOGIN_NAME", nullable = false)	
	private String loginName;
	
	@Column(name = "LOGIN_PASS", nullable = false)
	private String loginPass;
	
	
	@Column(name = "LOGIN_ROLE", nullable = false)	
	private String loginRole;
	
	
	@Column(name = "ENABLED", nullable = false)
	private int loginStatus;
	
	
	public int getLoginStatus() {
		return loginStatus;
	}


	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}


	@Column(name = "LOGIN_AVALI_POINTS", nullable = false)
	private Double availablePoints;
	
	@Column(name = "GOLDEN_PREDICT")	
	private String goldenPredict;

	public User(){}
	
	public User(String loginName, String loginPass, String loginRole,
			int loginStatus, Double availablePoints,String goldenPredict) {
		super();
		this.loginName = loginName;
		this.loginPass = loginPass;
		this.loginRole = loginRole;
		this.loginStatus = loginStatus;
		this.availablePoints = availablePoints;
		this.goldenPredict=goldenPredict;
	}


	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getLoginPass() {
		return loginPass;
	}


	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}


	public String getLoginRole() {
		return loginRole;
	}


	public void setLoginRole(String loginRole) {
		this.loginRole = loginRole;
	}





	public Double getAvailablePoints() {
		return availablePoints;
	}


	public void setAvailablePoints(Double availablePoints) {
		this.availablePoints = availablePoints;
	}

	
	public String getGoldenPredict() {
		return goldenPredict;
	}


	public void setGoldenPredict(String goldenPredict) {
		this.goldenPredict = goldenPredict;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((availablePoints == null) ? 0 : availablePoints.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (availablePoints == null) {
			if (other.availablePoints != null)
				return false;
		} else if (!availablePoints.equals(other.availablePoints))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", loginName=" + loginName + ", loginPass="
				+ loginPass + ", loginRole=" + loginRole + ", loginStatus="
				+ loginStatus + ", availablePoints=" + availablePoints
				+ ", goldenPredict=" + goldenPredict + "]";
	}




	
}
