package com.chess.messages;

import com.chess.messages.spec.Color;

import java.util.UUID;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseMessage {
    public UUID gameId;
    public String message = "";
    public Color color;
    public boolean ready;

    public ResponseMessage(UUID gameId, String message, Color color, boolean ready) {
        this.gameId = gameId;
        this.message = message;
        this.color = color;
        this.ready = ready;
    }

    public ResponseMessage(UUID gameId, Color color, boolean ready) {
        this.gameId = gameId;
        this.color = color;
        this.ready = ready;
    }

    public ResponseMessage() {
    }

    public ResponseMessage(final JsonObject jsonObject) {
        this.color = jsonObject.containsKey("color") ? Color.valueOf(jsonObject.getString("color")) : Color.WHITE;
        this.message = jsonObject.containsKey("message") ? jsonObject.getString("message") : null;
        this.gameId = jsonObject.containsKey("gameId") ? UUID.fromString(jsonObject.getString("gameId")) : null;
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

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
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
