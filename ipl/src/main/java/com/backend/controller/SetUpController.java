package com.backend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.service.SetUpService;

@RestController
public class SetUpController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SetUpService setUpService;

	@RequestMapping(value = "/setup/{fileName}", method = RequestMethod.GET)
	@Secured ("ROLE_ADMIN")
	public String setupDatabase(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@PathVariable("fileName") String fileName) {
		logger.info("Processing file-" + fileName);

		
		//ClassLoader classLoader = getClass().getClassLoader();
		//File file = new File(classLoader.getResource("user_master.sql").getFile());
		setUpService.setup(fileName);

		logger.info("DataBase setup Done-" + fileName);
		return "admin";
	}

}