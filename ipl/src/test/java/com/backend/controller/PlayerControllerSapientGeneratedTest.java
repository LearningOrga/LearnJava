package com.backend.controller;

import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import com.backend.service.PlayerService;
import java.util.ArrayList;
import org.mockito.MockitoAnnotations;
import com.backend.model.Player;
import org.springframework.ui.ModelMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class PlayerControllerSapientGeneratedTest {

    private final PlayerService playerServiceMock = mock(PlayerService.class, "playerService");

    private AutoCloseable autoCloseableMocks;

    @InjectMocks()
    private PlayerController target;

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    //Sapient generated method id: ${0f26c763-9551-37f5-8551-9cc0974fd82f}, hash: B7FEE95AC395100091AB2BCE21648F0A
    @Test()
    void getAllPlayersTest() {
        //Arrange Statement(s)
        target = new PlayerController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        List<Player> playerList = new ArrayList<>();
        doReturn(playerList).when(playerServiceMock).findAllPlayers();
        ModelMap modelMap = new ModelMap();
        
        //Act Statement(s)
        List<Player> result = target.getAllPlayers(modelMap);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo(playerList));
            verify(playerServiceMock).findAllPlayers();
        });
    }

    //Sapient generated method id: ${fe69fbc8-8492-388a-baec-1b9ba0e3653a}, hash: 30A8A48A840319F38C4BAE50FAA61719
    @Test()
    void getPlayerByIdTest() {
        //Arrange Statement(s)
        target = new PlayerController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        Player playerMock = mock(Player.class);
        doReturn(playerMock).when(playerServiceMock).findPlayerById(0);
        ModelMap modelMap = new ModelMap();
        
        //Act Statement(s)
        Player result = target.getPlayerById(modelMap, 0);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo(playerMock));
            verify(playerServiceMock).findPlayerById(0);
        });
    }
}
