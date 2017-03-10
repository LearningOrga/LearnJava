package com.ipl.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.backend.model.User;
import com.backend.service.UserService;

@RestController
public class LoginController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService loginMasterService;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView   homePage(ModelMap model) {
		model.addAttribute("greeting", "Hi, Welcome to Predict and Win. ");
		ModelAndView mav = new ModelAndView("welcome");
		mav.addObject(model);
		
		return mav;
	}

	@RequestMapping(value = { "/ipl_home" }, method = RequestMethod.GET)
	public ModelAndView iplehomePage(HttpServletRequest request, ModelMap model) {
		model.addAttribute("greeting", "Hi, Welcome to Predict and Win. ");
		request.getSession().setAttribute("loginName",
				getCompleteUserDetailss(model).getLoginName());
		request.getSession().setAttribute("userId",
				getCompleteUserDetailss(model).getId());
		request.getSession().setAttribute("totalRemainingPoints",
				getCompleteUserDetailss(model).getAvailablePoints());
		
		ModelAndView mav = new ModelAndView("ipl_home");
		mav.addObject(model);
		return mav;
		//return "ipl_home";
	}

	@RequestMapping(value = { "/summary" }, method = RequestMethod.GET)
	public ModelAndView summaryPage(ModelMap model) {
		logger.info("summary");
		ModelAndView mav = new ModelAndView("summary");
		mav.addObject(model);
		logger.info("summary");
		return mav;
		
	}

	@RequestMapping(value = { "/userReports" }, method = RequestMethod.GET)
	public ModelAndView reportsPage(ModelMap model) {
		ModelAndView mav = new ModelAndView("userReports");
		mav.addObject(model);
		return mav;
		
	}

	@RequestMapping(value = { "/winningRules" }, method = RequestMethod.GET)
	public ModelAndView winningRules(ModelMap model) {
		logger.info("Winning Rules");
		ModelAndView mav = new ModelAndView("winningRules");
		mav.addObject(model);
		return mav;
		
	}

	@RequestMapping(value = { "/manageUsers" }, method = RequestMethod.GET)
	public ModelAndView manageUsersPage(ModelMap model) {
		logger.debug("manageUsers");
		ModelAndView mav = new ModelAndView("manage_users");
		mav.addObject(model);
		return mav;
		
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		ModelAndView mav = new ModelAndView("admin");
		mav.addObject(model);
		return mav;
		
	}

	@RequestMapping(value = "/dba", method = RequestMethod.GET)
	public ModelAndView dbaPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		ModelAndView mav = new ModelAndView("dba");
		mav.addObject(model);
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logoutPage(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		ModelAndView mav = new ModelAndView("welcome");
				return mav;
	}

	@RequestMapping(value = "/user/{userName}", method = RequestMethod.GET)
	public @ResponseBody
	User getUserDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {

		User user = null;

		String userName = getPrincipal();
		user = loginMasterService.findUserByName(userName);

		return user;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public @ResponseBody
	User getCompleteUserDetailss(ModelMap model) {
		String userName = getPrincipal();
		return loginMasterService.findUserByName(userName);
	}

	@RequestMapping(value = "/user/getByid", method = RequestMethod.GET)
	public @ResponseBody
	User updateUserById(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("selUserId") int userId) {

		User user = null;

		user = loginMasterService.findUserById(userId);

		return user;
	}

	/*
	 * @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
	 * public @ResponseBody void deleteUserById(ModelMap
	 * model,@PathVariable("id") int userId) {
	 * System.out.println("Deletng-"+userId);
	 * loginMasterService.deleteUserById(userId); }
	 */

	

	@RequestMapping(value = "/user/all", method = RequestMethod.GET)
	public @ResponseBody
	List<User> getAllUsers(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {

		List<User> users = new ArrayList();

		users = loginMasterService.findAllUsers();

		return users;
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public ModelAndView accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		ModelAndView mav = new ModelAndView("accessDenied");
		mav.addObject(model);
		return mav;
		
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	

	// Spring Security see this :
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

}
