package ru.jru.module3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameServletTest {

    private GameServlet servlet;

    @BeforeEach
    void setUp() {
        servlet = new GameServlet();
    }

    @Test
    void testGetNextState_NullInputs_ReturnsStart() {
        assertEquals(GameState.START, servlet.getNextState(null, "1"));
        assertEquals(GameState.START, servlet.getNextState(GameState.START, null));
    }

    @Test
    void testGetNextState_StartToBridgeChoice() {
        GameState next = servlet.getNextState(GameState.START, "1");
        assertEquals(GameState.BRIDGE_CHOICE, next);
    }

    @Test
    void testGetNextState_StartToFailRejected() {
        GameState next = servlet.getNextState(GameState.START, "2");
        assertEquals(GameState.FAIL_REJECTED, next);
    }

    @Test
    void testGetNextState_BridgeToIdentityChoice() {
        GameState next = servlet.getNextState(GameState.BRIDGE_CHOICE, "1");
        assertEquals(GameState.IDENTITY_CHOICE, next);
    }

    @Test
    void testGetNextState_BridgeToFailNoNegotiations() {
        GameState next = servlet.getNextState(GameState.BRIDGE_CHOICE, "2");
        assertEquals(GameState.FAIL_NO_NEGOTIATIONS, next);
    }

    @Test
    void testGetNextState_IdentityToWinHome() {
        GameState next = servlet.getNextState(GameState.IDENTITY_CHOICE, "1");
        assertEquals(GameState.WIN_HOME, next);
    }

    @Test
    void testGetNextState_IdentityToFailLied() {
        GameState next = servlet.getNextState(GameState.IDENTITY_CHOICE, "2");
        assertEquals(GameState.FAIL_LIED, next);
    }
}