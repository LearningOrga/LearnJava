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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Match;
import com.backend.model.PlayResult;
import com.backend.service.MatchResultService;
import com.backend.service.MatchService;
import com.backend.service.PlayResultService;
import com.backend.service.RuleService;
import com.backend.service.UserService;

@RestController
public class ChartsController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PlayResultService playResultService;
	@Autowired
	MatchService matchService;


	@RequestMapping(value = "/testChart.png", method = RequestMethod.GET)
	public void renderPieChart(final HttpServletRequest request,
			final OutputStream stream, @RequestParam("ruleId") int ruleId,
			@RequestParam("matchId") int matchId,
			@RequestParam("dateString") String dateString) throws Exception {
		JFreeChart chart = createPieChart(matchId, ruleId);
		ChartUtilities.writeChartAsPNG(stream, chart, 700, 300);
	}

	@RequestMapping(value = "/testBarChart.png", method = RequestMethod.GET)
	public void testBarChart(final HttpServletRequest request,
			final OutputStream stream, @RequestParam("ruleId") int ruleId,
			@RequestParam("matchId") int matchId,
			@RequestParam("dateString") String dateString) throws Exception {
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
			}
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

	
	
	
}
