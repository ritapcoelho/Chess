package com.chess;

import com.chess.messages.ResponseMessage;
import com.chess.messages.spec.Color;
import com.chess.messages.spec.MessageType;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingDeque;

import javax.json.Json;
import javax.json.JsonReader;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ChessServerTest {
    private static final LinkedBlockingDeque<ResponseMessage> MESSAGES = new LinkedBlockingDeque<>();
    private static final String URI = "ws://localhost:8003/chess/";

    @BeforeEach
    void init() {
        MESSAGES.clear();
    }

    @Test
    public void testCreateAndJoin() throws URISyntaxException, IOException, DeploymentException, EncodeException, InterruptedException {
        Client client = getClient("A");
        UUID uuid;
        // User adds new game
        sendMessage(client, MessageType.ADD_GAME, Color.WHITE);
        ResponseMessage addResponseMessage = waitOnMessageAndPop();
        uuid = addResponseMessage.getGameId();
        Assertions.assertEquals(Color.WHITE, addResponseMessage.getColor());
        Assertions.assertNotNull(addResponseMessage.getGameId());
        Assertions.assertFalse(addResponseMessage.isReady());

        //new user joins game
        Client client1 = getClient("B");
        sendMessage(client1, MessageType.JOIN_GAME, Color.BLACK);

        ResponseMessage joinMessage = waitOnMessageAndPop();
        Assertions.assertEquals(uuid, joinMessage.getGameId());
        Assertions.assertTrue(joinMessage.isReady());
        client.session.close();
        client1.session.close();
    }

    private void sendMessage(Client client, MessageType type, Color color) throws IOException, EncodeException {
        client.sendMessage(
            Json.createObjectBuilder()
                .add("messageType", type.toString())
                .add("color", color.toString())
                .build().toString());
    }

    private Client getClient(final String user) throws URISyntaxException, IOException, DeploymentException {
        Client client = new Client();
        ContainerProvider.getWebSocketContainer().connectToServer(client, new URI(URI + user));
        return client;
    }

    synchronized ResponseMessage waitOnMessageAndPop() throws InterruptedException {
        Instant start = Instant.now();
        long maxWaitMilis = 3000;
        while (MESSAGES.isEmpty() && Instant.now().isBefore(start.plusMillis(maxWaitMilis))) {
            // wait
        }
        return MESSAGES.pop();
    }

    @ClientEndpoint
    public static class Client {

        private Session session;

        @OnOpen
        public void open(Session session) {
            this.session = session;
        }

        @OnMessage
        void message(String msg) {
            try (JsonReader reader = Json.createReader(new StringReader(msg))) {
                MESSAGES.add(new ResponseMessage(reader.readObject()));
            }
        }

        public void sendMessage(String message) throws IOException, EncodeException {
            this.session.getBasicRemote().sendObject(message);
        }
    }


}
