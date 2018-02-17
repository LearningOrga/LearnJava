package com.ipl.controller;

import com.ipl.model.Match;
import com.ipl.model.MatchResult;
import com.ipl.model.Rule;
import com.ipl.service.MatchResultService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MatchResultControllerTest {

    @Mock
    MatchResultService service;



    @Mock
    MessageSource message;

    @InjectMocks
    MatchResultController appController;

    @Spy
    List<MatchResult> matchResults = new ArrayList<MatchResult>();

    @Spy
    ModelMap model;


    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        matchResults = getSampleMatchResult();
    }

    @Test
    public void listMatchResults() {

        when(service.findAllRecords()).thenReturn(matchResults);
        Assert.assertEquals(appController.getAllRecords(model), matchResults);
        //TODO : if you set thn you can verify this as well
        // Assert.assertEquals(model.get("matches"), matches);
        verify(service, atLeastOnce()).findAllRecords();
    }


    @Test
    public void getMatchResultByMatchId() {
        when(service.findAllRecordsByMatchId(anyInt())).thenReturn(matchResults);
        Assert.assertEquals(appController.getMatchResultByMatchId(model, anyInt()), matchResults);
        //TODO : if you set thn you can verify this as well
        // Assert.assertEquals(model.get("matches"), matches);
        verify(service, atLeastOnce()).findAllRecordsByMatchId(anyInt());
    }





    public List<MatchResult>  getSampleMatchResult(){

        List<MatchResult> matchResults = new ArrayList<MatchResult>();



        MatchResult matchResult1 = new MatchResult();
        Match match1 = new Match();

        match1.setMatchDate("06-04-2017");
        match1.setMatchDay("Thursday");
        match1.setMatchDetails("Rising Pune Supergiants vs Mumbai Indians");
        match1.setMatchStatus("A");
        match1.setMatchTime("20:00 PM (2:30pm GMT)");
        match1.setMatchVenue("Pune");

        matchResult1.setMatchId(match1);

        Rule  rule1=new Rule();
        rule1.setRuleBasedOn("Team");
        rule1.setRuleDesc("Which team is winning");
        rule1.setRuleName("Winning Team");
        rule1.setRuleStatus("A");
        matchResult1.setRuleId(rule1);
        matchResult1.setRuleResult("Royal Challengers Bangalore");

        MatchResult matchResult2 = new MatchResult();
        Match match2 = new Match();

        //match.setId(6);
        match2.setMatchDate("05-04-2017");
        match2.setMatchDay("Wednesday");
        match2.setMatchDetails("Sunrisers Hyderabad vs Royal Challengers Bangalore");
        match2.setMatchStatus("A");
        match2.setMatchTime("20:00 PM (2:30pm GMT)");
        match2.setMatchVenue("Hyderabad");

        Rule  rule2=new Rule();
        rule2.setRuleBasedOn("Team");
        rule2.setRuleDesc("Which team is winning Toss");
        rule2.setRuleName("Toss Winning Team");
        rule2.setRuleStatus("A");
        matchResult2.setRuleId(rule2);
        matchResult2.setRuleResult("Sunrisers Hyderabad");
        matchResults.add(matchResult1);
        matchResults.add(matchResult2);

        return matchResults;
    }
}
