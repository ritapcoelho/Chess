package com.chess.messages;

import com.chess.ChessServer;

import java.util.Objects;

import javax.json.Json;
import javax.json.JsonObject;

public class Message {

    private MessageType messageType;
    protected String msg;
    protected String gameId;
    private String username;
    private Color color;


    public Message(final JsonObject jsonObject) {
        String type = jsonObject.getString("messageType");
        Objects.requireNonNull(type);
        this.messageType = MessageType.valueOf(type);

        String color = jsonObject.getString("color");
        this.color = color == null ? Color.WHITE : Color.valueOf(color);

        this.msg = !jsonObject.containsValue("msg") ? null: jsonObject.getString("msg");
        this.gameId = !jsonObject.containsValue("gameId") ? null: jsonObject.getString("gameId");
        this.username = !jsonObject.containsValue("username") ? null: jsonObject.getString("username");
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("msg", this.msg)
            .add("gameId", this.gameId)
            .add("username", this.username)
            //.add("password", this.password)
            .build();
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getMsg() {
        return msg;
    }

    public String getGameId() {
        return gameId;
    }

    public String getUsername() {
        return username;
    }

    public Color getColor() {
        return color;
    }
}
