package com.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;



@Entity
@RevisionEntity(UserRevisionListener.class)
public class RevInfoUser extends DefaultRevisionEntity {

	@Column(name = "USERNAME", nullable = false)
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}