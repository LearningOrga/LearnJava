package com.backend.dao;

import com.backend.model.Match;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class MatchDaoImplTest extends IPLDaoImplBaseTest{

	@Autowired
	MatchDao matchDao;

	@Override
	protected IDataSet getDataSet() throws Exception{
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Match.xml"));
		return dataSet;
	}

	/* In case you need multiple datasets (mapping different tables) and you do prefer to keep them in separate XML's
	@Override
	protected IDataSet getDataSet() throws Exception {
	  IDataSet[] datasets = new IDataSet[] {
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Employee.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Benefits.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Departements.xml"))
	  };
	  return new CompositeDataSet(datasets);
	}
	*/

	@Test
	public void findById(){
		Assert.assertNotNull(matchDao.findMatchById(1));

	}


	@Test
	public void saveMatch(){
		matchDao.saveMatch(getSampleMatch());
		Assert.assertEquals(matchDao.findAllMatches().size(), 4);
	}

	@Test
	public void deleteMatchById(){
		matchDao.removeMatchById(1);
		Assert.assertEquals(matchDao.findAllMatches().size(), 2);
	}

	@Test
	public void deleteByWrongId(){
		matchDao.removeMatchById(3);
		Assert.assertEquals(matchDao.findAllMatches().size(), 2);
	}

	@Test
	public void findAllMatches(){
		Assert.assertEquals(matchDao.findAllMatches().size(), 3);
	}



	public Match getSampleMatch(){
		Match match = new Match();
		//match.setId(6);
		match.setMatchDate("06-04-2017");
		match.setMatchDay("Thursday");
		match.setMatchDetails("Rising Pune Supergiants vs Mumbai Indians");
		match.setMatchStatus("A");
		match.setMatchTime("20:00 PM (2:30pm GMT)");
		match.setMatchVenue("Pune");
		return match;
	}

}
