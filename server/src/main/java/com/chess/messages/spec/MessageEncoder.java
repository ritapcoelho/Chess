package com.chess.messages.spec;

import com.chess.messages.ResponseMessage;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<ResponseMessage> {

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public String encode(ResponseMessage object) {
        return object.toJson().toString();
    }
}
