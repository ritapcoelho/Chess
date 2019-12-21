package com.chess.messages;

import java.util.function.Function;

public interface MessageProcessor {
    String PLAYER_JOINED = "Player %s joined the game...";

    ResponseMessage processNewGame(Message message);
    ResponseMessage processEnterGame(Message message);

    default Function<Message, ResponseMessage> func(Message message) {
        switch (message.getMessageType()) {
            case ADD_GAME: return this::processNewGame;
            case JOIN_GAME: return this::processEnterGame;
            default:
                throw new RuntimeException("messageType has no processor");
        }
    }
}
