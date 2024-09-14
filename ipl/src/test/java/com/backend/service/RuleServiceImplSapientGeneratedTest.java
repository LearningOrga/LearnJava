package com.backend.service;

import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import com.backend.dao.RuleDao;
import java.util.ArrayList;
import org.mockito.MockitoAnnotations;
import com.backend.model.Rule;
import static org.mockito.Mockito.doNothing;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class RuleServiceImplSapientGeneratedTest {

    private final RuleDao ruleDaoMock = mock(RuleDao.class, "ruleDao");

    private AutoCloseable autoCloseableMocks;

    @InjectMocks()
    private RuleServiceImpl target;

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    //Sapient generated method id: ${4f3b5a90-1cdc-3f17-8afb-722d0cbfcbeb}, hash: D7CA6A6FA27AE474DCA91B28A421A49F
    @Test()
    void saveRuleTest() {
        //Arrange Statement(s)
        target = new RuleServiceImpl();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        Rule ruleMock = mock(Rule.class);
        doNothing().when(ruleDaoMock).saveRule(ruleMock);
        
        //Act Statement(s)
        target.saveRule(ruleMock);
        
        //Assert statement(s)
        assertAll("result", () -> verify(ruleDaoMock).saveRule(ruleMock));
    }

    //Sapient generated method id: ${7635e196-9723-3e0b-91f1-457a9637d354}, hash: 9DB5BCB971F8087A455698E13982213C
    @Test()
    void findAllRulesTest() {
        //Arrange Statement(s)
        target = new RuleServiceImpl();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        List<Rule> ruleList = new ArrayList<>();
        doReturn(ruleList).when(ruleDaoMock).findAllRules();
        
        //Act Statement(s)
        List<Rule> result = target.findAllRules();
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo(ruleList));
            verify(ruleDaoMock).findAllRules();
        });
    }

    //Sapient generated method id: ${d178c65a-793f-32b1-97f4-942a835c024d}, hash: CEF699A9CDD8B0335F4B1D74346E0D34
    @Test()
    void findRuleByIdTest() {
        //Arrange Statement(s)
        target = new RuleServiceImpl();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        Rule ruleMock = mock(Rule.class);
        doReturn(ruleMock).when(ruleDaoMock).findRuleById(0);
        
        //Act Statement(s)
        Rule result = target.findRuleById(0);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo(ruleMock));
            verify(ruleDaoMock).findRuleById(0);
        });
    }
}
