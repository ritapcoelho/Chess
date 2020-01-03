package com.chess.messages;

import java.util.function.Consumer;

public interface MessageProcessor {
    String PLAYER_JOINED = "Player %s joined the game...";

    void processStartGameMessage(Message message);

    default Consumer<Message> func(Message message) {
        switch (message.getMessageType()) {
            case CREATE_GAME:
                return this::processStartGameMessage;
            default:
                throw new RuntimeException("messageType has no processor");
        }
    }
}
