package com.backend.controller;

import com.backend.service.SetUpService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class SetUpController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SetUpService setUpService;

	@RequestMapping(value = "/setup/{fileName}", method = RequestMethod.GET)
	public String setupDatabase(HttpServletRequest request,
								HttpServletResponse response, ModelMap model,
								@PathVariable("fileName") String fileName) {
		System.out.println("Processing file-" + fileName);

		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName+".sql").getFile());
		setUpService.setup(fileName);

		System.out.println("DataBase setup Done-" + fileName);
		return "admin";
	}

}