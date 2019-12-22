package com.chess.player.control;

import com.chess.ChessServer;

import java.util.Objects;

public class Player {
    private final String username;
    private ChessServer session;

    public Player(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public ChessServer getSession() {
        return session;
    }

    public void setSession(ChessServer session) {
        this.session = session;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(username, player.username) &&
            Objects.equals(session, player.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, session);
    }
}
