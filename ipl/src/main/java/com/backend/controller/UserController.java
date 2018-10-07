package com.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@Secured ("ROLE_ADMIN")
	public @ResponseBody	
	void updateUser(ModelMap model, @RequestBody User user) {


		loginMasterService.updateUser(user);
		logger.info("updated Successfully");

		// ("updatedSuccessfully");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@Secured ("ROLE_ADMIN")
	public @ResponseBody
	void addUser(ModelMap model, @RequestBody User user) {
		logger.debug("Entry");
		loginMasterService.saveUser(user);
		logger.debug("exit");

	}

}
