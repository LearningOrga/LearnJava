package com.ipl.web.controller;

import java.awt.Color;
import java.awt.Font;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.backend.model.PlayResult;
import com.backend.service.PlayResultService;

@Controller
public class ReportController {

	@Autowired
	PlayResultService playResultService;

	@RequestMapping(value = "/userWinLossReportBC.png", method = RequestMethod.GET)
	public void userWinLossReportBarChart(final HttpServletRequest request,
			final OutputStream stream, @RequestParam("userId") int userId)
			throws Exception {
	
		JFreeChart chart = createBarChart(userId);
		ChartUtilities.writeChartAsPNG(stream, chart, 1000, 300);
	}

	@RequestMapping(value = "/userWinLossProgressLC.png", method = RequestMethod.GET)
	public void userWinLossProgressLineChart(final HttpServletRequest request,
			final OutputStream stream, @RequestParam("userId") int userId,
			@RequestParam("ruleId") int ruleId) throws Exception {

		JFreeChart chart = createLineChart(userId, ruleId);
		ChartUtilities.writeChartAsPNG(stream, chart, 1000, 300);
	}

	// This chart will show all the wins / losses for the selected user.
	// Will count the total no of wins / losses for the rules.
	// Should display 4 bars. 2 for each rule. and 1 for each win and loss.
	private JFreeChart createBarChart(int userId) {
		List<PlayResult> playResult = playResultService
				.findAllRecordsByUserId(userId);

		Map prepDataMap = new HashMap();
		for (PlayResult result : playResult) {
			// dataset.addValue(result.getTotalPointsEarned(),
			// result.getRuleId().getRuleName(),
			// result.getMatchId().getMatchDetails() );
			String tempKey = result.getRuleResult() + "~~"
					+ result.getRuleId().getRuleName();
			if (prepDataMap.containsKey(tempKey)) {
				double tempDouble = Double.parseDouble(prepDataMap.get(tempKey)
						.toString());
				prepDataMap.put(tempKey, tempDouble + 1 + "");
			} else {
				prepDataMap.put(tempKey, "1");
			}
		}
		System.out.println("prepDataMap:" + prepDataMap);

		// create the dataset...
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		Set keySet = prepDataMap.keySet();
		Iterator keySetItr = keySet.iterator();

		while (keySetItr.hasNext()) {
			String tempMapKey = keySetItr.next().toString();
			String[] tempMapKeyArr = tempMapKey.split("~~");
			dataset.addValue(
					Double.parseDouble(prepDataMap.get(tempMapKey).toString()),
					tempMapKeyArr[0], tempMapKeyArr[1]);
		}

		final JFreeChart chart1 = ChartFactory.createBarChart("Bar Chart", // chart
				// title
				"Rule", // domain axis label
				"Win / Loss", // range axis label
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

	// This should create a line chart. Per Match and per rule ID.
	// This should be a sin wave type graph.
	private JFreeChart createLineChart(int userId, int ruleId) {

		Map param = new HashMap();
		param.put("userId", userId);
		param.put("ruleId", ruleId);

		List<PlayResult> playResult = playResultService
				.findAllRecordsByParams(param);
		System.out.println("Playresult-"+playResult);
		// create the dataset...
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (PlayResult result : playResult) {
			System.out.println("inside for" + result);
			dataset.addValue(result.getTotalPointsEarned(), result.getRuleId()
					.getRuleName(), result.getMatchId().getMatchDetails() + "");
		}

		final JFreeChart chart1 = ChartFactory.createLineChart("Line Chart", // chart
				// title
				"Rule", // domain axis label
				"Win / Loss", // range axis label
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
		rangeAxis.setAutoRangeIncludesZero(true);

		final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();

		return chart1;
	}
}
