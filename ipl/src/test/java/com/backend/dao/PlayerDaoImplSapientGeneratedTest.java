package com.backend.dao;

import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.mockito.MockitoAnnotations;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import com.backend.model.Player;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;
import static org.mockito.Mockito.doNothing;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
import org.junit.jupiter.api.Disabled;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class PlayerDaoImplSapientGeneratedTest {

    private final EntityManager entityManagerMock = mock(EntityManager.class, "entityManager");

    private AutoCloseable autoCloseableMocks;

    @InjectMocks()
    private PlayerDaoImpl target;

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    //Sapient generated method id: ${ad4f003d-97f3-3a07-adbc-377eec621864}, hash: 6D27DF690A87028A1A57F60B539648D9
    @Test()
    void savePlayerTest() {
        //Arrange Statement(s)
        target = spy(new PlayerDaoImpl());
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        Player playerMock = mock(Player.class);
        doNothing().when(target).persist(playerMock);
        //Act Statement(s)
        target.savePlayer(playerMock);
        //Assert statement(s)
        assertAll("result", () -> verify(target).persist(playerMock));
    }

    //Sapient generated method id: ${d8868101-f101-3c90-86a8-23f8efb1ee9e}, hash: 86A42041824715D9797B6F71545A9647
    @Disabled()
    @Test()
    void findAllPlayersTest() {
        //Arrange Statement(s)
        target = new PlayerDaoImpl();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        EntityManager entityManagerMock2 = mock(EntityManager.class);
        CriteriaBuilder criteriaBuilderMock = mock(CriteriaBuilder.class);
        doReturn(criteriaBuilderMock).when(entityManagerMock2).getCriteriaBuilder();
        CriteriaQuery<Player> criteriaQueryMock = mock(CriteriaQuery.class);
        doReturn(criteriaQueryMock).when(criteriaBuilderMock).createQuery(Player.class);
        Root<Player> rootMock = mock(Root.class);
        doReturn(rootMock).when(criteriaQueryMock).from(Player.class);
        CriteriaQuery criteriaQueryMock2 = mock(CriteriaQuery.class);
        doReturn(criteriaQueryMock2).when(criteriaQueryMock).select(rootMock);
        TypedQuery typedQueryMock = mock(TypedQuery.class);
        doReturn(typedQueryMock).when(entityManagerMock2).createQuery(criteriaQueryMock);
        List<Player> playerList = new ArrayList<>();
        doReturn(playerList).when(typedQueryMock).getResultList();
        //Act Statement(s)
        List<Player> result = target.findAllPlayers();
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo(playerList));
            verify(entityManagerMock2).getCriteriaBuilder();
            verify(criteriaBuilderMock).createQuery(Player.class);
            verify(criteriaQueryMock).from(Player.class);
            verify(criteriaQueryMock).select(rootMock);
            verify(entityManagerMock2).createQuery(criteriaQueryMock);
            verify(typedQueryMock).getResultList();
        });
    }

    //Sapient generated method id: ${f8276e56-9d03-35bc-8815-31e822d45856}, hash: BDBF5C9BE996F2B1F11B4A2607CFDC8B
    @Disabled()
    @Test()
    void findPlayerByIdTest() {
        //Arrange Statement(s)
        target = new PlayerDaoImpl();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        EntityManager entityManagerMock2 = mock(EntityManager.class);
        CriteriaBuilder criteriaBuilderMock = mock(CriteriaBuilder.class);
        doReturn(criteriaBuilderMock).when(entityManagerMock2).getCriteriaBuilder();
        CriteriaQuery<Player> criteriaQueryMock = mock(CriteriaQuery.class);
        doReturn(criteriaQueryMock).when(criteriaBuilderMock).createQuery(Player.class);
        Root<Player> rootMock = mock(Root.class);
        doReturn(rootMock).when(criteriaQueryMock).from(Player.class);
        Path pathMock = mock(Path.class);
        doReturn(pathMock).when(rootMock).get("id");
        CriteriaQuery criteriaQueryMock2 = mock(CriteriaQuery.class);
        Predicate predicateMock = mock(Predicate.class);
        doReturn(criteriaQueryMock2).when(criteriaQueryMock).where(predicateMock);
        doReturn(predicateMock).when(criteriaBuilderMock).equal(pathMock, 1);
        TypedQuery<Player> typedQueryMock = mock(TypedQuery.class);
        doReturn(typedQueryMock).when(entityManagerMock2).createQuery(criteriaQueryMock);
        Player playerMock = mock(Player.class);
        doReturn(playerMock).when(typedQueryMock).getSingleResult();
        //Act Statement(s)
        Player result = target.findPlayerById(1);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo(playerMock));
            verify(entityManagerMock2).getCriteriaBuilder();
            verify(criteriaBuilderMock).createQuery(Player.class);
            verify(criteriaQueryMock).from(Player.class);
            verify(rootMock).get("id");
            verify(criteriaQueryMock).where(predicateMock);
            verify(criteriaBuilderMock).equal(pathMock, 1);
            verify(entityManagerMock2).createQuery(criteriaQueryMock);
            verify(typedQueryMock).getSingleResult();
        });
    }
}
