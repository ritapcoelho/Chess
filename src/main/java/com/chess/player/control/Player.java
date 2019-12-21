package com.chess.player.control;

import com.chess.ChessServer;

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
}
