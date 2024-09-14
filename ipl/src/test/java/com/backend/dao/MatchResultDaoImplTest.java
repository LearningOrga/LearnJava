package com.backend.dao;

/*
public class MatchResultDaoImplTest extends IPLDaoImplBaseTest{

	@Autowired
	MatchResultDao matchResultDao;

	@Autowired
	MatchDao matchDao;
	@Autowired
	RuleDao ruleDao;

	@Before
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

}*/
