package com.chess.player.control;

import com.chess.ChessServer;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerService {

    // save players in redis or something like that
    Map<String, Player> players = new ConcurrentHashMap<>();

    public Player getPlayer(final String username) {
        Optional<Player> player = Optional.ofNullable(players.get(username));
        return player.orElseGet(() -> {
            addPlayer(username);
            return players.get(username);
        });
    }

    public Player addPlayer(final String username) {
        return players.put(username, new Player(username));
    }
}
