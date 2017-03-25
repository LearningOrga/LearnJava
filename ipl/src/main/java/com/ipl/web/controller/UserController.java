package com.ipl.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.User;
import com.backend.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService loginMasterService;

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public @ResponseBody
	void updateUser(ModelMap model, @RequestBody User user) {


		loginMasterService.updateUser(user);
		logger.info("updated Successfully");

		// ("updatedSuccessfully");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	void addUser(ModelMap model, @RequestBody User user) {
		logger.debug("Entry");
		loginMasterService.saveUser(user);
		logger.debug("exit");

	}

}
