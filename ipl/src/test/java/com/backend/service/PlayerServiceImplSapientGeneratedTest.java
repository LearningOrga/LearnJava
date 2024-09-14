package com.backend.service;

import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import com.backend.dao.PlayerDao;
import java.util.ArrayList;
import org.mockito.MockitoAnnotations;
import com.backend.model.Player;
import static org.mockito.Mockito.doNothing;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class PlayerServiceImplSapientGeneratedTest {

    private final PlayerDao playerDaoMock = mock(PlayerDao.class, "playerDao");

    private AutoCloseable autoCloseableMocks;

    @InjectMocks()
    private PlayerServiceImpl target;

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    //Sapient generated method id: ${ad4f003d-97f3-3a07-adbc-377eec621864}, hash: 4B935A7B64BE3BA8182629C16AAE0A4F
    @Test()
    void savePlayerTest() {
        //Arrange Statement(s)
        target = new PlayerServiceImpl();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        Player playerMock = mock(Player.class);
        doNothing().when(playerDaoMock).savePlayer(playerMock);
        
        //Act Statement(s)
        target.savePlayer(playerMock);
        
        //Assert statement(s)
        assertAll("result", () -> verify(playerDaoMock).savePlayer(playerMock));
    }

    //Sapient generated method id: ${d8868101-f101-3c90-86a8-23f8efb1ee9e}, hash: 94AA505A5DBCEEBE372BCE2A2CEB6D3A
    @Test()
    void findAllPlayersTest() {
        //Arrange Statement(s)
        target = new PlayerServiceImpl();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        List<Player> playerList = new ArrayList<>();
        doReturn(playerList).when(playerDaoMock).findAllPlayers();
        
        //Act Statement(s)
        List<Player> result = target.findAllPlayers();
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo(playerList));
            verify(playerDaoMock).findAllPlayers();
        });
    }

    //Sapient generated method id: ${f8276e56-9d03-35bc-8815-31e822d45856}, hash: 602D2EBF669B95EECF26AC50FB0B5F78
    @Test()
    void findPlayerByIdTest() {
        //Arrange Statement(s)
        target = new PlayerServiceImpl();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        Player playerMock = mock(Player.class);
        doReturn(playerMock).when(playerDaoMock).findPlayerById(0);
        
        //Act Statement(s)
        Player result = target.findPlayerById(0);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo(playerMock));
            verify(playerDaoMock).findPlayerById(0);
        });
    }
}
