package com.ipl.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.backend.service.SetUpService;

@Controller
public class SetUpController {

	@Autowired
	SetUpService setUpService;

	@RequestMapping(value = "/setup/{fileName}", method = RequestMethod.GET)
	public String setupDatabase(HttpServletRequest request,
			HttpServletResponse response,ModelMap model,@PathVariable("fileName") String fileName) {
		//("DataBase setup started-"+fileName);
		
		
		setUpService.setup(fileName);
		
		//("DataBase setup Done-"+fileName);
		return "admin";
	}

}