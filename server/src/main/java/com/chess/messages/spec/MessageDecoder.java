package com.chess.messages.spec;

import com.chess.messages.Message;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Message> {

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Message decode(String jsonMessage) throws DecodeException {
        try(JsonReader reader = Json.createReader(new StringReader(jsonMessage))) {
            return new Message(reader.readObject());
        }
    }

    @Override
    public boolean willDecode(String jsonMessage) {
        try(JsonReader reader = Json.createReader(new StringReader(jsonMessage))) {
            reader.readObject();
            return true;
        }
    }
}
