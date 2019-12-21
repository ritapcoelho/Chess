package com.chess.game;

import com.chess.messages.Message;
import com.chess.messages.spec.Color;
import com.chess.player.control.Player;

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

    @ConsumeEvent("game-started")
    public void gameStarted(final Game game) {
        notStartedGames.remove(game);
    }

    public Game createGame(Player player, Color color) {
        final Game game = Game.startGame(player, color);
        games.put(game.getId(), game);
        return game;
    }

    public void startExistingGame(Game game, Player player) {
        games.get(game.getId()).addPlayerAndStart(player);
        notStartedGames.remove(game);
    }

    @ConsumeEvent("new-game")
    public void addGame(final Game game) {
        games.put(game.getId(), game);
        if (game.notStarted()) {
            notStartedGames.add(game);
        }
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
