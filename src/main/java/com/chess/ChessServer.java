package com.chess;

import com.chess.game.Game;
import com.chess.game.GameManager;
import com.chess.messages.Message;
import com.chess.messages.MessageProcessor;
import com.chess.messages.spec.MessageDecoder;
import com.chess.messages.spec.MessageEncoder;
import com.chess.messages.ResponseMessage;
import com.chess.player.control.Player;
import com.chess.player.control.PlayerService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import io.vertx.core.eventbus.EventBus;


@ServerEndpoint(
    value = "/chess/{username}",
    encoders = {MessageEncoder.class},
    decoders = {MessageDecoder.class})
public class ChessServer implements MessageProcessor {
    private Session session;
    private Player player;
    private Set<UUID> gameIds = new HashSet<>();

    @Inject
    EventBus bus;

    @Inject
    PlayerService playerService;

    @Inject
    GameManager gameManager;

    @OnMessage
    public ResponseMessage onMessage(Message message, Session session, @PathParam("username") String username) {
        this.session = session;
        this.player = playerService.getPlayer(username);
        return this.func(message).apply(message);
    }

    @Override
    public ResponseMessage processNewGame(Message message) {
        final Game game = Game.startGame(player, message.getColor());
        final UUID gameId = game.getId();
        gameIds.add(gameId);
        bus.send("new-game", game);
        this.player.setSession(this);
        return new ResponseMessage(gameId, "", game.getCreatorColor(), false);
    }

    @Override
    public ResponseMessage processEnterGame(Message message) {
        return getGame(message)
            .map(game -> {
                gameIds.add(game.getId());
                game.addPlayer(this.player);
                this.player.setSession(this);
                sendMessage(String.format(PLAYER_JOINED, this.player.getUsername()), game.getOtherPlayer(this.player).getSession());
                bus.send("game-started", game);
                return new ResponseMessage(game.getId(), "", game.getChallengerColor(), true);
            })
            .orElseGet(() -> processNewGame(message));
    }

    private Optional<Game> getGame(Message message) {
        if (message.getGameId() != null) {
            return gameManager.getGame(UUID.fromString(message.getGameId()));
        } else {
            return gameManager.getNonStartedGame();
        }
    }

    public void sendMessage(String message, ChessServer chessServer) {
        chessServer.getSession().getAsyncRemote().sendObject(message, result -> {
            if (result.getException() != null) {
                System.out.println("Unable to send message: " + result.getException());
            }
        });
    }

    public Session getSession() {
        return session;
    }
}
