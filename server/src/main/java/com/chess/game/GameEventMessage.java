package com.chess.game;

import com.chess.game.Game;
import com.chess.messages.spec.Color;
import com.chess.player.control.Player;

public class GameEventMessage {

    private final Player player;
    private final Game game;
    private final Color color;

    public GameEventMessage(Player player, Game game, Color color) {
        this.player = player;
        this.game = game;
        this.color = color;
    }

    public Player getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }

    public Color getColor() {
        return color;
    }
}
