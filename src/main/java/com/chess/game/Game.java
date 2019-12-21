package com.chess.game;

import com.chess.messages.spec.Color;
import com.chess.player.control.Player;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Game {
    private final UUID id;
    private GameState state; //--> ENUM
    private Player whitePlayer;
    private Player blackPlayer;
    private final LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Color createrColor;

    private Game(Player whitePlayer, Player blackPlayer) {
        this.id = UUID.randomUUID();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.state = GameState.WAITING_PLAYER;
        this.createdOn = LocalDateTime.now();
        this.updatedOn = LocalDateTime.now();
        this.createrColor = whitePlayer == null ? Color.BLACK : Color.WHITE;
    }

    static Game startGame(Player player, Color color) {
        Objects.requireNonNull(player);
        Objects.requireNonNull(color);
        Player whitePlayer = color == Color.WHITE ? player : null;
        Player blackPlayer = color == Color.BLACK ? player : null;
        return new Game(whitePlayer, blackPlayer);
    }

    public Color getChallengerColor() {
        return createrColor == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public Color getCreatorColor() {
        return createrColor;
    }

    synchronized void addPlayerAndStart(Player player) {
        if (notStarted()) {
            if (this.whitePlayer == null) {
                this.whitePlayer = player;
            } else {
                this.blackPlayer = player;
            }
            state = GameState.STARTED;
        } else {
            throw new RuntimeException("Game already started!");
        }
    }

    public boolean notStarted() {
        return state == GameState.WAITING_PLAYER;
    }

    public UUID getId() {
        return id;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
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

    public Player getOtherPlayer(final Player requestingPlayer) {
        if (blackPlayer.equals(requestingPlayer)) {
            return whitePlayer;
        } else {
            return blackPlayer;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) &&
            state == game.state &&
            Objects.equals(whitePlayer, game.whitePlayer) &&
            Objects.equals(blackPlayer, game.blackPlayer) &&
            Objects.equals(createdOn, game.createdOn) &&
            Objects.equals(updatedOn, game.updatedOn) &&
            createrColor == game.createrColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state, whitePlayer, blackPlayer, createdOn, updatedOn, createrColor);
    }
}
