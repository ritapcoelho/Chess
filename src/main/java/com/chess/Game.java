package com.chess;

import com.chess.messages.Color;
import com.chess.player.control.Player;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Game {
    //private static final String UNDEFINED = "<?>";
    private final UUID id;
    private final String state; //--> ENUM
    private Player whitePlayer;
    private Player blackPlayer;
    private final LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    private Game(Player whitePlayer, Player blackPlayer) {
        this.id = UUID.randomUUID();
        this.whitePlayer = whitePlayer;
        this.state = "STARTED";
        this.createdOn = LocalDateTime.now();
        this.updatedOn = LocalDateTime.now();
    }

    public static Game startGame(Player player, Color color) {
        Objects.requireNonNull(player);
        Objects.requireNonNull(color);
        Player whitePlayer = color == Color.WHITE ? player : null;
        Player blackPlayer = color == Color.BLACK ? player : null;
        return new Game(whitePlayer, blackPlayer);
    }

    public UUID getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }
}
