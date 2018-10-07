package com.backend.controller;

import com.backend.model.Match;
import com.backend.service.MatchService;
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

public class MatchControllerTest {

    @Mock
    MatchService service;

    @Mock
    MessageSource message;

    @InjectMocks
    MatchController appController;

    @Spy
    List<Match> matches = new ArrayList<Match>();

    @Spy
    ModelMap model;


    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        matches = getMatchList();
    }

    @Test
    public void listMatches() {
        Match match = matches.get(0);
        when(service.findAllMatches()).thenReturn(matches);
        Assert.assertEquals(appController.getAllMatches(model), matches);
        //TODO : if you set thn you can verify this as well
        // Assert.assertEquals(model.get("matches"), matches);
        verify(service, atLeastOnce()).findAllMatches();
    }


    @Test
    public void getMatchById() {
        Match match = matches.get(0);
        when(service.findMatchById(anyInt())).thenReturn(match);
        Assert.assertEquals(appController.getMatchById(model, anyInt()), match);
        //TODO : if you set thn you can verify this as well
        // Assert.assertEquals(model.get("matches"), matches);
        verify(service, atLeastOnce()).findMatchById(anyInt());
    }

   // @Test
    public void saveMatchWithSuccess() {
        doNothing().when(service).saveMatch(any(Match.class));
        Assert.assertEquals(appController.addMatch(model, eq("06-04-2017"), eq("Thrusay"), eq("Rising Pune Supergiants vs Mumbai Indians"), eq("A"), eq("20:00 PM (2:30pm GMT)"), eq("Pune")), "Success");
        Assert.assertEquals(model.get("Success"), "Success");
    }

    @Test
    public void deleteMatch() {
        doNothing().when(service).removeMatchById(anyInt());
        Assert.assertEquals(appController.deleteMatchById(model, 12), "Success");
        verify(service, atLeastOnce()).removeMatchById(anyInt());
    }

    public List<Match> getMatchList() {
        List<Match> matches = new ArrayList<Match>();
        Match match1 = new Match();
        //match.setId(6);
        match1.setMatchDate("06-04-2017");
        match1.setMatchDay("Thursday");
        match1.setMatchDetails("Rising Pune Supergiants vs Mumbai Indians");
        match1.setMatchStatus("A");
        match1.setMatchTime("20:00 PM (2:30pm GMT)");
        match1.setMatchVenue("Pune");

        Match match2 = new Match();
        //match.setId(6);
        match2.setMatchDate("05-04-2017");
        match2.setMatchDay("Wednesday");
        match2.setMatchDetails("Sunrisers Hyderabad vs Royal Challengers Bangalore");
        match2.setMatchStatus("A");
        match2.setMatchTime("20:00 PM (2:30pm GMT)");
        match2.setMatchVenue("Hyderabad");
        ;

        matches.add(match1);
        matches.add(match1);
        return matches;
    }
}
