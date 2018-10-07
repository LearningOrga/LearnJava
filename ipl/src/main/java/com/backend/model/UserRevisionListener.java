package com.backend.model;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;



public class UserRevisionListener implements RevisionListener {

	private String USERNAME = "deefaultused";

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