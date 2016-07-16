package com.backend;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.backend.model.Match;
import com.backend.service.MatchService;
import com.ipl.config.MainAppConfig;

public class MatchMasterMain {

	public static void main(String args[]) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(MainAppConfig.class);

		MatchService service = (MatchService) context.getBean("matchMasterService");

		

		/*
		 * Get all employees list from database
		 */
		List<Match> matches = service.findAllMatches();
		for (Match match : matches) {
			//(match);
		}

		

	

		context.close();
	}
}
