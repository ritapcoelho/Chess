package com.chess.game;

import com.chess.messages.spec.Color;
import com.chess.player.control.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    @Test
    void addGame() {
        Player player1 = new Player("rpc");

        Game game = Game.startGame(player1, Color.WHITE);
        game.setState(GameState.WAITING_PLAYER);

        assertTrue(game.notStarted());
        GameManager manager = new GameManager();
        manager.addGame(game);

        assertEquals(game,manager.getNonStartedGame().get());
    }
}