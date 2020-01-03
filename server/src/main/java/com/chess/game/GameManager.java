package com.chess.game;

import java.util.Deque;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.vertx.ConsumeEvent;
import io.undertow.util.FastConcurrentDirectDeque;

@ApplicationScoped
public class GameManager {

    private Map<UUID, Game> games = new ConcurrentHashMap<>();
    private Deque<Game> notStartedGames = new FastConcurrentDirectDeque<>();

    @ConsumeEvent("create.game")
    public Game createGame(GameEventMessage msg) {
        final Game game = Game.startGame(msg.getPlayer(), msg.getColor());
        games.put(game.getId(), game);
        notStartedGames.add(game);
        return game;
    }

    @ConsumeEvent("start.game")
    public boolean startExistingGame(GameEventMessage msg) {
        games.get(msg.getGame().getId()).addPlayerAndStart(msg.getPlayer());
        notStartedGames.remove(msg.getGame());
        return true;
    }

    public Optional<Game> getGame(final UUID gameId) {
        return Optional.ofNullable(games.get(gameId));
    }

    public Optional<Game> getNonStartedGame() {
        try {
            return Optional.of(notStartedGames.pop());
        } catch (final Exception e) {
            return Optional.empty();
        }
    }
}
