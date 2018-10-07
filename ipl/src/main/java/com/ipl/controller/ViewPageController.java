package com.ipl.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ViewPageController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@RequestMapping(value = "/addMatchPoints", method = RequestMethod.GET)
	public ModelAndView addMatchPoints(HttpServletRequest request, ModelMap model,
			@RequestParam("matchid") int matchId) {
		request.getSession().setAttribute("matchId", matchId);

		ModelAndView mav = new ModelAndView("add_points");
		mav.addObject(model);
		
		return mav;

	}

	@RequestMapping(value = "/viewMatchPoints", method = RequestMethod.GET)
	public ModelAndView viewMatchPoints(HttpServletRequest request, ModelMap model,
			@RequestParam("matchid") int matchId) {
		request.getSession().setAttribute("matchId", matchId);

		ModelAndView mav = new ModelAndView("view_points");
		mav.addObject(model);
		
		return mav;
	}

	@RequestMapping(value = "/matchResults", method = RequestMethod.GET)
	public ModelAndView matchResult(HttpServletRequest request, ModelMap model,
			@RequestParam("matchid") int matchId) {
		request.getSession().setAttribute("matchId", matchId);

		ModelAndView mav = new ModelAndView("match_result");
		mav.addObject(model);
		
		return mav;
		
	}

	@RequestMapping(value = "/matchResultAdmin", method = RequestMethod.GET)
	public ModelAndView matchResultAdmin(HttpServletRequest request, ModelMap model,
			@RequestParam("matchid") int matchId) {
		request.getSession().setAttribute("matchId", matchId);

		ModelAndView mav = new ModelAndView("match_results_admin");
		mav.addObject(model);
		
		return mav;
		
	}
}