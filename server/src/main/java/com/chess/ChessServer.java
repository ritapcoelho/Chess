package com.chess;

import com.chess.game.Game;
import com.chess.game.GameManager;
import com.chess.game.GameEventMessage;
import com.chess.messages.Message;
import com.chess.messages.MessageProcessor;
import com.chess.messages.ResponseMessage;
import com.chess.messages.spec.MessageDecoder;
import com.chess.messages.spec.MessageEncoder;
import com.chess.player.control.Player;
import com.chess.player.control.PlayerService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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
    public void onMessage(Message message, Session session, @PathParam("username") String username) {
        this.session = session;
        this.player = playerService.getPlayer(username);
        this.func(message).accept(message);
    }

    @Override
    public void processStartGameMessage(Message message) {
        gameManager.getNonStartedGame()
            .map(this::enterExistingGame)
            .orElseGet(() -> createNewGame(message))
            .thenAccept(responseMessage -> sendMessage(responseMessage, this));
    }

    private CompletableFuture<ResponseMessage> enterExistingGame(Game game) {
        return enterGame(game).thenApply(aBoolean ->
            new ResponseMessage(game.getId(), game.getChallengerColor(), true));
    }

    public CompletableFuture<ResponseMessage> createNewGame(Message message) {
        CompletableFuture<ResponseMessage> fut = new CompletableFuture<>();
        bus.request("create.game", new GameEventMessage(player, null, message.getColor()), ar -> {
            if (ar.succeeded()) {
                Game game = (Game) ar.result().body();
                addToGamesAndSetSession(game);
                ResponseMessage responseMessage = new ResponseMessage(game.getId(), game.getCreatorColor(), false);
                fut.complete(responseMessage);
            }
        });
        return fut;
    }

    private CompletableFuture<Boolean> enterGame(final Game game) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        addToGamesAndSetSession(game);
        bus.request("start.game", new GameEventMessage(player, game, null), ar -> {
                // send message to game creator
                sendMessage(String.format(PLAYER_JOINED, this.player.getUsername()), game.getOtherPlayer(this.player).getSession());
                fut.complete(((Boolean) ar.result().body()));
            }
        );
        return fut;
    }

    private void addToGamesAndSetSession(final Game game) {
        this.player.setSession(this);
        gameIds.add(game.getId());
    }

    public void sendMessage(String message, ChessServer chessServer) {
        chessServer.getSession().getAsyncRemote().sendObject(message, result -> {
            if (result.getException() != null) {
                System.out.println("Unable to send message: " + result.getException());
            }
        });
    }

    public void sendMessage(Object message, ChessServer chessServer) {
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
