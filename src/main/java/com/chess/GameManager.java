package com.chess;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GameManager {

    private Map<UUID, Game> games = new ConcurrentHashMap<>();

    public void addGame(final Game game) {
        games.put(game.getId(), game);
    }

    public Game getGame(final UUID gameId) {
        return games.get(gameId);
    }
}
