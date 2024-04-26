package com.backend.service;

import com.backend.dao.MatchDao;
import com.backend.dao.MatchResultDao;
import com.backend.dao.RuleDao;
import com.backend.model.MatchResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


public class MatchResultServiceImplTest {

	@Mock
	MatchResultDao dao;

	@Mock
	MatchDao matchDao;
	@Mock
	RuleDao ruleDao;
	
	@InjectMocks
	MatchResultServiceImpl matchResultService;
	
	@Spy
	List<MatchResult> matcheResults = new ArrayList<MatchResult>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		matcheResults = getSampleMatchResult();
	}

	@Test
	public void findByMatchId(){

		when(dao.findAllRecordsByMatchId(anyInt())).thenReturn(matcheResults);
		Assert.assertEquals(matchResultService.findAllRecordsByMatchId(1),matcheResults);
	}

	@Test
	public void findByRuleId(){
		when(dao.findAllRecordsByRuleId(anyInt())).thenReturn(matcheResults);
		Assert.assertEquals(matchResultService.findAllRecordsByRuleId(1),matcheResults);
	}



	@Test
	public void saveMatchResults(){
		doNothing().when(dao).saveMatchResult(any(MatchResult.class));
		matchResultService.saveMatchResult(any(MatchResult.class));
		verify(dao, atLeastOnce()).saveMatchResult(any(MatchResult.class));
	}

	@Test
	public void updateMatchResults(){
		doNothing().when(dao).updateMatchResult(any(MatchResult.class));
		matchResultService.updateMatchResult(any(MatchResult.class));
		verify(dao, atLeastOnce()).updateMatchResult(any(MatchResult.class));
	}

	@Test
	public void updateMatchResultsAnotherway(){
		MatchResult matchResult = matcheResults.get(0);
		when(dao.findAllRecordsByMatchId(anyInt())).thenReturn(matcheResults);
		matchResultService.updateMatchResult(matchResult);
		verify(dao, atLeastOnce()).findAllRecordsByMatchId(anyInt());
	}



	public List<MatchResult>  getSampleMatchResult(){

		List<MatchResult> matchResults = new ArrayList<MatchResult>();

		MatchResult matchResult1 = new MatchResult();
		//match.setId(6);
		matchResult1.setMatchId(matchDao.findMatchById(1));
		matchResult1.setRuleId(ruleDao.findRuleById(1));
		matchResult1.setRuleResult("Royal Challengers Bangalore");

		MatchResult matchResult2 = new MatchResult();
		//match.setId(6);
		matchResult2.setMatchId(matchDao.findMatchById(2));
		matchResult2.setRuleId(ruleDao.findRuleById(1));
		matchResult2.setRuleResult("Sunrisers Hyderabad");
		matchResults.add(matchResult1);
		matchResults.add(matchResult2);

		return matchResults;
	}
	
}
