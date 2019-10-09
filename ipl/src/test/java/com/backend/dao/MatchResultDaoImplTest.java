package com.backend.dao;

import com.backend.model.MatchResult;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.h2.tools.Server;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLException;


public class MatchResultDaoImplTest extends IPLDaoImplBaseTest{

	@Autowired
	MatchResultDao matchResultDao;

	@Autowired
	MatchDao matchDao;
	@Autowired
	RuleDao ruleDao;

	@BeforeTest
	public void initTest() throws SQLException {
		Server webServer = Server.createWebServer("-web",
				"-webAllowOthers", "-webPort", "9092");
		webServer.start();
	}


	@Override
	protected IDataSet getDataSet() throws Exception {
	  IDataSet[] datasets = new IDataSet[] {
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Match.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Rule.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("MatchResult.xml"))
	  };
	  return new CompositeDataSet(datasets);
	}



	@Test
	public void findAllMatchResults(){
		Assert.assertEquals(matchResultDao.findAllRecords().size(), 2);
	}

	@Test
	public void saveMatchResults(){
		matchResultDao.saveMatchResult(getSampleMatchResult());
		Assert.assertEquals(matchResultDao.findAllRecords().size(), 3);
	}

	@Test
	public void getMatchResultsById(){

		Assert.assertEquals(matchResultDao.findAllRecordsByMatchId(1).size(), 1);
	}


	public MatchResult getSampleMatchResult(){



		MatchResult matchResult = new MatchResult();
		//match.setId(6);
		matchResult.setMatchId(matchDao.findMatchById(1));
		matchResult.setRuleId(ruleDao.findRuleById(1));
		matchResult.setRuleResult("Royal Challengers Bangalore");
		return matchResult;
	}

}
