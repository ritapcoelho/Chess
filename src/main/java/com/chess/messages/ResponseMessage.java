package com.chess.messages;

import com.chess.messages.spec.Color;

import java.util.UUID;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseMessage {
    public final UUID gameId;
    public final String message;
    public final Color color;
    public final boolean ready;

    public ResponseMessage(UUID gameId, String message, Color color, boolean ready) {
        this.gameId = gameId;
        this.message = message;
        this.color = color;
        this.ready = ready;
    }

    public ResponseMessage(final JsonObject jsonObject) {
        this.color = jsonObject.containsKey("color") ? Color.valueOf(jsonObject.getString("color")) : Color.WHITE;
        this.message = !jsonObject.containsKey("message") ? null: jsonObject.getString("message");
        this.gameId = !jsonObject.containsKey("gameId") ? null: UUID.fromString(jsonObject.getString("gameId"));
        this.ready = jsonObject.getBoolean("ready");
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("color", this.color.toString())
            .add("gameId", this.gameId.toString())
            .add("ready", this.ready)
            .add("message", this.message)
            .build();
    }

    public UUID getGameId() {
        return gameId;
    }

    public String getMessage() {
        return message;
    }

    public Color getColor() {
        return color;
    }

    public boolean isReady() {
        return ready;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
            "gameId=" + gameId +
            ", message='" + message + '\'' +
            ", color=" + color +
            ", ready=" + ready +
            '}';
    }
}
