package com.backend.rev;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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