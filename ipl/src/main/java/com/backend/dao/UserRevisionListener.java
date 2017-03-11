package com.backend.dao;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.backend.model.RevInfoUser;

public class UserRevisionListener implements RevisionListener {

	private String USERNAME = "Jitendra";

	@Override
	public void newRevision(Object revisionEntity) {
		RevInfoUser exampleRevEntity = (RevInfoUser) revisionEntity;

		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			USERNAME = ((UserDetails) principal).getUsername();
		} else {
			USERNAME = principal.toString();
		}
		exampleRevEntity.setUsername(USERNAME);
	}

}