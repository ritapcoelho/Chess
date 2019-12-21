package com.chess.messages;

import com.chess.messages.spec.Color;

import java.util.UUID;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseMessage {
    public UUID gameId;
    public String message;
    public Color color;
    public boolean ready;

    public ResponseMessage(UUID gameId, String message, Color color, boolean ready) {
        this.gameId = gameId;
        this.message = message;
        this.color = color;
        this.ready = ready;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("color", this.color.toString())
            .add("gameId", this.gameId.toString())
            .add("ready", this.ready)
            .add("message", this.message)
            .build();
    }
}
