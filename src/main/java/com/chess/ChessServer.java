package com.chess;

import com.chess.messages.Message;
import com.chess.messages.MessageDecoder;
import com.chess.messages.MessageEncoder;
import com.chess.messages.ResponseMessage;
import com.chess.player.control.Player;
import com.chess.player.control.PlayerService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(
    value = "/chess/{username}",
    encoders = {MessageEncoder.class},
    decoders = {MessageDecoder.class})
public class ChessServer {
    private Session session;
    private Player player;
    private Set<UUID> gameIds = new HashSet<>();

    @Inject
    PlayerService playerService;

    @Inject
    GameManager gameManager;

    @OnMessage
    public ResponseMessage onMessage(Message message, Session session,  @PathParam("username") String username) {
        this.session = session;
        this.player = playerService.getPlayer(username);
        return processNewGame(message);
    }

    private ResponseMessage processNewGame(Message message) {
        final Game game = Game.startGame(player, message.getColor());
        final UUID gameId = game.getId();
        gameIds.add(gameId);
        gameManager.addGame(game);
        this.player.setSession(this);
        return new ResponseMessage(gameId, "", message.getColor(), false);
    }

    public void sendMessage(ResponseMessage message) {
        session.getAsyncRemote().sendObject(message, result -> {
            if (result.getException() != null) {
                System.out.println("Unable to send message: " + result.getException());
            }
        });
    }

    public Session getSession() {
        return session;
    }
}
