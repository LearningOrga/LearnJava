package com.backend.controller;

import com.backend.model.User;
import com.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService loginMasterService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	@Secured ("ROLE_ADMIN")
	public @ResponseBody	
	void updateUser(ModelMap model, @RequestBody User user) {
		user.setLoginPass(passwordEncoder.encode(user.getLoginPass()));
		loginMasterService.updateUser(user);
		logger.info("updated Successfully");
		// ("updatedSuccessfully");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@Secured ("ROLE_ADMIN")
	public @ResponseBody
	void addUser(ModelMap model, @RequestBody User user) {
		logger.debug("Entry");
		user.setLoginPass(passwordEncoder.encode(user.getLoginPass()));
		loginMasterService.saveUser(user);
		logger.debug("exit");
	}


}
