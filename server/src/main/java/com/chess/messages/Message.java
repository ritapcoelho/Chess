package com.chess.messages;

import com.chess.messages.spec.Color;
import com.chess.messages.spec.MessageType;

import java.util.Objects;

import javax.json.Json;
import javax.json.JsonObject;

public class Message {

    private MessageType messageType;
    protected String msg;
    protected String gameId;
    private String username;
    private Color color;

    public Message(MessageType messageType, String msg, String gameId, String username, Color color) {
        this.messageType = messageType;
        this.msg = msg;
        this.gameId = gameId;
        this.username = username;
        this.color = color;
    }

    public Message(MessageType messageType)  {
        this.messageType = messageType;
    }

    public Message(final JsonObject jsonObject) {
        String type = jsonObject.getString("messageType");
        Objects.requireNonNull(type);
        this.messageType = MessageType.valueOf(type);

        this.color = jsonObject.containsKey("color") ? Color.valueOf(jsonObject.getString("color")) : Color.WHITE;

        this.msg = !jsonObject.containsKey("msg") ? null: jsonObject.getString("msg");
        this.gameId = !jsonObject.containsKey("gameId") ? null: jsonObject.getString("gameId");
        this.username = !jsonObject.containsKey("username") ? null: jsonObject.getString("username");
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("msg", this.msg)
            .add("messageType", this.messageType.toString())
            .add("color", this.color.toString())
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
