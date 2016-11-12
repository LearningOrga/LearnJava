package com.ipl.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

	@Autowired
	UserService loginMasterService;

	
	
	@RequestMapping( method = RequestMethod.POST, value = "/update")
	public @ResponseBody
	void updateUser(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("selUserId") int userId,
			@RequestParam("selloginName") String selloginName,
			@RequestParam("selloginRole") String selloginRole,
			@RequestParam("selloginStatus") String selloginStatus,
			@RequestParam("selavailablePoints") String selavailablePoints,
			@RequestParam("selloginPass") String selloginPass,
			@RequestParam("selgoldenPredict") String selGoldenPredict) {

		User user = loginMasterService.findUserById(userId);

		user.setLoginName(selloginName);
		user.setLoginRole(selloginRole);
		user.setLoginStatus(Integer.parseInt(selloginStatus));
		user.setAvailablePoints(Double.parseDouble(selavailablePoints));
		user.setLoginPass(selloginPass);
		user.setGoldenPredict(selGoldenPredict);
		loginMasterService.updateUser(user);

		// ("updatedSuccessfully");
	}

	
}
