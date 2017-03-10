package com.ipl.web.controller;

import java.awt.Color;
import java.awt.Font;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.backend.model.Match;
import com.backend.model.MatchResult;
import com.backend.model.PlayResult;
import com.backend.model.Summary;
import com.backend.model.User;
import com.backend.service.MatchResultService;
import com.backend.service.MatchService;
import com.backend.service.PlayResultService;
import com.backend.service.RuleService;
import com.backend.service.UserService;

@Controller
public class PlayResultController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PlayResultService playResultService;

	@Autowired
	UserService userService;

	@Autowired
	RuleService ruleService;

	@Autowired
	MatchService matchService;

	@Autowired
	MatchResultService matchResultService;

	@RequestMapping(value = "/playResult/all", method = RequestMethod.GET)
	public @ResponseBody
	List<PlayResult> getAllRecords(ModelMap model) {

		List<PlayResult> results = playResultService.findAllRecords();

		return results;
	}

	@RequestMapping(value = "/playResult/allForSummary", method = RequestMethod.GET)
	public @ResponseBody
	List<Summary> getALlRecordsForSummary(ModelMap model) {

		List<Summary> userSumm = new ArrayList<Summary>();
		Summary summ = null;

		List<User> users = userService.findAllUsers();
		for (User user : users) {
			// ("ids+"+user.getId());

			int userId = user.getId();
			int numberofWins;
			int numberOfLoss;
			DecimalFormat df = new DecimalFormat("#.##");
			double winnLossPer = 0;

			List<PlayResult> results = playResultService
					.findAllRecordsByUserId(userId);

			summ = new Summary();

			summ.setUserId(userId);
			summ.setUserName(user.getLoginName());
			summ.setTotalPoints(user.getAvailablePoints());
			summ.setTotalPointsInvested(playResultService
					.getTotalInvestedPointsByUserId(userId));
			summ.setTotalPointsEarned(playResultService
					.getTotalEarnedPointsByUserId(userId));

			summ.setRule1Wins(playResultService.getTotalRulewWins(1, userId));
			summ.setRule2Wins(playResultService.getTotalRulewWins(2, userId));
			
			numberofWins = playResultService.getTotalWins(userId);
			numberOfLoss = playResultService.getTotalLoss(userId);

			summ.setTotalNumberWins(numberofWins);
			int totalPlayed = numberofWins + numberOfLoss;
			summ.setTotalNumberPredicted(totalPlayed);
			winnLossPer = (double) numberofWins / totalPlayed;
			winnLossPer = winnLossPer * 100;
			summ.setWinLossPer(Double.parseDouble(df.format(winnLossPer)));
			userSumm.add(summ);

		}
		// ("adding+"+summ.toString());

		return userSumm;
	}

	@RequestMapping(value = "/playResult/{matchId}/{ruleId}", method = RequestMethod.GET)
	public @ResponseBody
	List<PlayResult> getPlayResultByMatchIdAndRuleId(ModelMap model,
			@PathVariable("matchId") int matchId,
			@PathVariable("ruleId") int ruleId) {

		// (matchId);
		// (" ----- " + ruleId);
		Map param = new HashMap();
		param.put("matchId", matchId);
		param.put("ruleId", ruleId);

		List<PlayResult> results = playResultService
				.findAllRecordsByParams((param));
		// ("total entries-" + results.size());

		playResultService.updateByResult(calculateIndicativePoints(results,
				matchId));

		return results;
	}

	@RequestMapping(value = "/playResult/byMatchId", method = RequestMethod.GET)
	public @ResponseBody
	List<PlayResult> getPlayResultByUserId(ModelMap model,
			@RequestParam("matchId") int matchId) {

		// (matchId);

		Map param = new HashMap();
		param.put("matchId", matchId);

		List<PlayResult> results = playResultService
				.findAllRecordsByParams((param));
		// ("total entries-" + results.size());

		return results;
	}

	@RequestMapping(value = "/reconcileAllUsersPoints", method = RequestMethod.GET)
	@ResponseBody
	public List<User> reconcilePlayResultByUserId(HttpServletRequest request,
			HttpServletResponse response,ModelMap model,
			@RequestParam("matchId") int matchId) {

		List<User> users = new ArrayList();
		

		users = userService.findAllUsers();
		for (User user : users) {
			Map params = new HashMap();
			params.put("matchId", matchId);
			params.put("userId", user.getId());

			List<PlayResult> results = playResultService
					.findAllRecordsByParams(params);
			// ("total entries-" + results.size());
			Double totalLossOrWin = 0.0;
			for (PlayResult result : results) {
				Double totalPointsEarned = result.getTotalPointsEarned();
				if (totalPointsEarned == null) {
					totalPointsEarned = 0.0;
				}

				totalLossOrWin = totalLossOrWin + totalPointsEarned;
			}
			user.setAvailablePoints(user.getAvailablePoints() + totalLossOrWin);
			userService.updateUser(user);
		}
		
		// TODO: this list may not be updated so if you are planning to use get
		// one
		return users;
	}

	@RequestMapping(value = "/reconcile", method = RequestMethod.GET)
	public @ResponseBody
	List<PlayResult> getReconcileData(HttpServletRequest request,
			HttpServletResponse response,ModelMap model,
			@RequestParam("selMatchId") int matchId,
			@RequestParam("selRuleId") int ruleId) {

		// TODO: update match result in play result from match result
		MatchResult matchresult = matchResultService
				.findAllRecordsByRuleIdnadMatchId(ruleId, matchId);

		// (matchId);
		// (" ----- " + ruleId);
		Map param = new HashMap();
		param.put("matchId", matchId);
		param.put("ruleId", ruleId);

		List<PlayResult> results=new ArrayList();
		
		

		results = playResultService
				.findAllRecordsByParams((param));

		// Update this list first with result
		// (results.size());
		for (PlayResult result : results) {
			result.setRuleResult(matchresult.getRuleResult());
			// TODO: this call not required but lets see
			// playResultService.updateResult(result);

		}

		playResultService.updateByResult(calculateTotalPoints(results, matchId,
				ruleId));
		
		return results;
	}

	/**
	 * THis method will be called from AJAX on the input page.
	 * 
	 * @param playResult
	 * @return
	 */
	private List<PlayResult> calculateIndicativePoints(
			List<PlayResult> playResult, int matchId) {
		Map pointsInvested = new HashMap();
		Map indicativePoints = new HashMap();

		Match match = matchService.findMatchById(matchId);
		String matchName = match.getMatchDetails();
		int indedofVs = matchName.indexOf("vs");

		String matchSplit1 = matchName.substring(0, indedofVs);
		String matchSplit2 = matchName.substring(indedofVs + 3);

		pointsInvested.put(matchSplit1.trim(), "0");
		pointsInvested.put(matchSplit2.trim(), "0");

		for (PlayResult result : playResult) {

			String ruleValue = result.getRuleValue();
			if (pointsInvested.containsKey(ruleValue)) {
				double tempPoints = Double.parseDouble(pointsInvested.get(
						ruleValue).toString());
				pointsInvested.put(ruleValue,
						tempPoints + result.getPointsInvested());
			}
		}

		// (pointsInvested);

		for (PlayResult result : playResult) {
			Set keyList = pointsInvested.keySet();
			Iterator keyListItr = keyList.iterator();
			String val1, val2;
			// ("keyListItr::" + keyList.toString());
			val1 = keyListItr.next().toString();
			val2 = keyListItr.next().toString();

			if (val1.equals(result.getRuleValue())) {
				double temp = result.getPointsInvested()
						/ Double.parseDouble(pointsInvested.get(
								result.getRuleValue()).toString());
				double temp1 = Double.parseDouble(pointsInvested.get(val2)
						.toString());
				double ptsInvested = result.getPointsInvested();

				// (temp + " --------------------------");
				double result2 = temp * temp1;
				// (result2 + " --------------------------");

				double result3 = result2 + ptsInvested;
				// (result3 + " --------------------------");

				result.setIndicativePoints(result3);

			} else {
				double temp = result.getPointsInvested()
						/ Double.parseDouble(pointsInvested.get(
								result.getRuleValue()).toString());
				double temp1 = Double.parseDouble(pointsInvested.get(val1)
						.toString());
				double ptsInvested = result.getPointsInvested();

				// (temp + " --------------------------");
				double result2 = temp * temp1;
				// (result2 + " --------------------------");

				double result3 = result2 + ptsInvested;
				// (result3 + " --------------------------");

				result.setIndicativePoints(result3);
			}

		}
		// ("playResult" + playResult.toString());
		return playResult;
	}

	/**
	 * THis method will be called from Reconcile method
	 * 
	 * @param playResult
	 * @return
	 */
	private List<PlayResult> calculateTotalPoints(List<PlayResult> playResult,
			int matchId, int ruleId) {
		Map pointsInvested = new HashMap();
		Map indicativePoints = new HashMap();

		for (PlayResult result : playResult) {
			String ruleValue = result.getRuleValue();
			if (pointsInvested.containsKey(ruleValue)) {
				double tempPoints = Double.parseDouble(pointsInvested.get(
						ruleValue).toString());
				pointsInvested.put(ruleValue,
						tempPoints + result.getPointsInvested());
			} else {
				pointsInvested.put(ruleValue, result.getPointsInvested());
			}
		}

		String wininTeam = matchResultService.findAllRecordsByRuleIdnadMatchId(
				ruleId, matchId).getRuleResult();
		// (wininTeam + "-----------------------------------");
		for (PlayResult result : playResult) {
			// String wininTeam = result.getResult();

			if (result.getRuleValue().equals(wininTeam)) {
				result.setResult("WIN");
			} else {
				result.setResult("LOSS");
			}

			Set keyList = pointsInvested.keySet();
			Iterator keyListItr = keyList.iterator();
			String val1, val2;

			// val1 = keyListItr.next().toString();
			// val2 = keyListItr.next().toString();

			int indedofVs = result.getMatchId().getMatchDetails().indexOf("vs");

			val1 = result.getMatchId().getMatchDetails()
					.substring(0, indedofVs).trim();
			val2 = result.getMatchId().getMatchDetails()
					.substring(indedofVs + 3).trim();
			// ("Val1-" + val1);

			// ("Val2-" + val2);

			// (wininTeam + val1 + result.getRuleValue());

			if ((result.getRuleValue().equals(wininTeam))) {
				double temp, temp1;

				if (val1.equals(wininTeam)) {
					temp = result.getPointsInvested()
							/ Double.parseDouble(pointsInvested.get(val1)
									.toString());
					temp1 = Double.parseDouble(pointsInvested.get(val2)
							.toString());
				} else {
					temp = result.getPointsInvested()
							/ Double.parseDouble(pointsInvested.get(val2)
									.toString());
					temp1 = Double.parseDouble(pointsInvested.get(val1)
							.toString());
				}
				double ptsInvested = result.getPointsInvested();

				// (temp + " --------------------------");
				double result2 = temp * temp1;
				// (result2 + " --------------------------");

				// double result3 = result2 + ptsInvested;
				// //(result3 + " --------------------------");

				// (result.getRuleValue() + " ---------> "
				/*
				 * + result.getPointsInvested() + " ------------ " + result2);
				 */

				result.setTotalPointsEarned(result2);

			} else {
				// double temp = result.getPointsInvested() /
				// Double.parseDouble(pointsInvested.get(result.getRuleValue()).toString());
				// double temp1 =
				// Double.parseDouble(pointsInvested.get(val1).toString());
				// double ptsInvested = result.getPointsInvested();
				//
				// //(temp + " --------------------------");
				// double result2 = temp * temp1;
				// //(result2 + " --------------------------");
				//
				// //(result.getRuleValue() + " ---------> "+
				// result.getPointsInvested() + " ------------ "+ result2);

				result.setTotalPointsEarned(result.getPointsInvested() * -1);
			}

		}

		return playResult;
	}

	@RequestMapping(value = "/playResult/add", method = RequestMethod.GET)
	public @ResponseBody
	List<PlayResult> getResultByPara(HttpServletRequest request,
			ModelMap model, @RequestParam("selRuleId") int ruleId,
			@RequestParam("selTeamName") String selTeamName,
			@RequestParam("selMatchId") int matchId,
			@RequestParam("selPointsInv") String pointsInvested,
			@RequestParam("selMatchTime") String selMatchTime) {

		List updatedPlayResult = null;
		Match match = matchService.findMatchById(matchId);
		// ("matchStatus-"+match.getMatchStatus());
		if (match.getMatchStatus().equals("A")) {
			// For timehandling
			DateFormat formatter = null;
			formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

			String matDateTime = "";
			try {
				Date convertedDate = (Date) formatter.parse(selMatchTime);
				matDateTime = formatter.format(convertedDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Date matchDate = new Date(formatter.parse(selMatchTime));

			// if (checkTiming(matDateTime)){

			Map param = new HashMap();
			param.put("matchId", matchId);
			param.put("ruleId", ruleId);
			param.put("userId", userService.findUserByName(getPrincipal())
					.getId());

			Map customParam = new HashMap();
			customParam.put("matchId", matchId);
			customParam.put("userId", userService
					.findUserByName(getPrincipal()).getId());

			// (" Param=================" + param.toString());

			List playResultList = playResultService
					.findAllRecordsByParams(param);

			// ("playResultList:" + playResultList);
			PlayResult playResult;

			if (playResultList.size() == 0) {

				playResult = new PlayResult();
				playResult
						.setUserId(userService.findUserByName(getPrincipal()));
				playResult.setRuleId(ruleService.findRuleById(ruleId));
				playResult.setRuleValue(selTeamName);
				playResult.setMatchId(matchService.findMatchById(matchId));
				playResult
						.setPointsInvested(Double.parseDouble(pointsInvested));

				playResultService.savePlayResult(playResult);

			} else {
				playResult = (PlayResult) playResultList.get(0);
				playResult
						.setUserId(userService.findUserByName(getPrincipal()));
				playResult.setRuleId(ruleService.findRuleById(ruleId));
				playResult.setRuleValue(selTeamName);
				playResult.setMatchId(matchService.findMatchById(matchId));
				playResult
						.setPointsInvested(Double.parseDouble(pointsInvested));

				playResultService.updateResult(playResult);
			}

			// return updated;

			getPlayResultByMatchIdAndRuleId(null, matchId, ruleId);

			updatedPlayResult = playResultService
					.findAllRecordsByParams(customParam);
			// ("updatedPlayResult:" + updatedPlayResult);
			// model.addAttribute("updatedPlayReesult", updatedPlayResult);
			request.getSession().setAttribute("updatedPlayResult",
					updatedPlayResult);

		}
		return updatedPlayResult;
	}

	@RequestMapping(value = "/testChart.png", method = RequestMethod.GET)
	public void renderPieChart(final HttpServletRequest request,
			final OutputStream stream, @RequestParam("ruleId") int ruleId,
			@RequestParam("matchId") int matchId,
			@RequestParam("dateString") String dateString) throws Exception {

		// ("JFREE------------------------------------"

		JFreeChart chart = createPieChart(matchId, ruleId);
		ChartUtilities.writeChartAsPNG(stream, chart, 700, 300);
	}

	@RequestMapping(value = "/testBarChart.png", method = RequestMethod.GET)
	public void testBarChart(final HttpServletRequest request,
			final OutputStream stream, @RequestParam("ruleId") int ruleId,
			@RequestParam("matchId") int matchId,
			@RequestParam("dateString") String dateString) throws Exception {
		// ("test========================================"
		JFreeChart chart = createBarChart1(ruleId, matchId);
		ChartUtilities.writeChartAsPNG(stream, chart, 1000, 300);
	}

	private JFreeChart createPieChart(int matchId, int ruleId) throws Exception {

		Map pointsInvested = new HashMap();

		Map param = new HashMap();
		param.put("matchId", matchId);
		param.put("ruleId", ruleId);

		Match match = matchService.findMatchById(matchId);
		String matchName = match.getMatchDetails();
		int indedofVs = matchName.indexOf("vs");

		String matchSplit1 = matchName.substring(0, indedofVs);
		String matchSplit2 = matchName.substring(indedofVs + 3);

		pointsInvested.put(matchSplit1.trim(), "0");
		pointsInvested.put(matchSplit2.trim(), "0");

		List<PlayResult> playResult = playResultService
				.findAllRecordsByParams((param));

		for (PlayResult result : playResult) {

			String ruleValue = result.getRuleValue();
			if (pointsInvested.containsKey(ruleValue)) {
				double tempPoints = Double.parseDouble(pointsInvested.get(
						ruleValue).toString());
				pointsInvested.put(ruleValue,
						tempPoints + result.getPointsInvested());
			}/*
			 * else { pointsInvested.put(ruleValue, result.getPointsInvested());
			 * }
			 */
		}

		// ("Points invested" + pointsInvested);

		Set keyList = pointsInvested.keySet();
		Iterator keyListItr = keyList.iterator();
		String val1, val2;

		val1 = keyListItr.next().toString();
		val2 = keyListItr.next().toString();

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(val1, new Double(pointsInvested.get(val1).toString()));
		dataset.setValue(val2, new Double(pointsInvested.get(val2).toString()));

		String chartTitle = "Test Chart";
		JFreeChart nomiChart = ChartFactory.createPieChart(chartTitle, dataset,
				false, true, false);
		PiePlot plot = (PiePlot) nomiChart.getPlot();
		StandardPieSectionLabelGenerator labels = new StandardPieSectionLabelGenerator(
				"{0} = {1} ({2})");
		plot.setLabelGenerator(labels);
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No data available");
		plot.setLabelGap(0.02);
		nomiChart.getTitle().setFont(new Font("Dialog", Font.PLAIN, 16));
		return nomiChart;
	}

	private JFreeChart createBarChart1(int ruleId, int matchId)
			throws Exception {
		// Comment all the other hardcoded values above.

		// We need to search with Match ID and Rule ID as the table will have
		// multiple records.
		// Need to pass Match ID to this method from the calling method.
		// The same needs to be done for the piechart also. I have not done the
		// changes in pie chart as am not sure if that will work directly.
		Map param = new HashMap();
		param.put("matchId", matchId);
		param.put("ruleId", ruleId);

		List<PlayResult> playResult = playResultService
				.findAllRecordsByParams((param));

		// create the dataset...
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (PlayResult result : playResult) {
			dataset.addValue(result.getPointsInvested(), result.getUserId()
					.getLoginName(), result.getRuleValue());
		}
		// create the chart...
		final JFreeChart chart1 = ChartFactory.createBarChart("Bar Chart", // chart
																			// title
				"Team", // domain axis label
				"Points Invested", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);
		chart1.setBackgroundPaint(Color.white);
		chart1.getTitle().setFont(new Font("Dialog", Font.PLAIN, 16));
		final CategoryPlot plot = chart1.getCategoryPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setRange(0.0,
				plot.getRangeAxis().getRange().getUpperBound() + 15);
		final BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);

		final CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions
				.createUpRotationLabelPositions(Math.PI / 6.0));

		return chart1;
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

	private boolean checkTiming(String matchDateTime) {
		DateFormat formatter = null;
		Date convertedDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

		TimeZone timeZone1 = TimeZone.getTimeZone("IST");
		sdf.setTimeZone(timeZone1);

		//
		formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
			convertedDate = (Date) formatter.parse(matchDateTime);

			// ("Match Date  ---- > "
			// + formatter.format(convertedDate));

			// IST Time zone
			Calendar calendar = new GregorianCalendar(timeZone1);
			// calendar.setTimeZone(timeZone1);
			Date d = calendar.getTime();

			// IST Time Zone

			String[] matchDate = formatter.format(convertedDate).split(" ");
			String[] currDate = sdf.format(d).split(" ");

			if (matchDate[0].equals(currDate[0])) {
				// ("Date is Same");
				// (matchDate[1] + " ----- " + currDate[1]);

				String[] matchTime = matchDate[1].split(":");
				String[] currTime = currDate[1].split(":");

				if (Integer.parseInt(matchTime[0]) > Integer
						.parseInt(currTime[0])) {
					// ("Current Hour is less than match time");
					if (Integer.parseInt(matchTime[0]) - 1 == Integer
							.parseInt(currTime[0])) {
						// ("TIME UP TIME UP");
						return false;
					}
				} else {
					System.out
							.println("Match Time is more than current time --------- Abort");
					return false;

				}
			} else {
				// //("Entry is not allowed. Wrong Date");
				return true;
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} // all done

	}
}
