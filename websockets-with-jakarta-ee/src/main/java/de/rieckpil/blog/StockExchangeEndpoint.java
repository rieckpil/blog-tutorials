package de.rieckpil.blog;

import javax.json.JsonObject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/stocks", decoders = {JSONTextDecoder.class}, encoders = {JSONTextEncoder.class})
public class StockExchangeEndpoint {

    private static Set<Session> sessions = new HashSet<>();

    public static void broadcastMessage(JsonObject message) {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocket opened: " + session.getId());
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(JsonObject message, Session session) {
        System.out.println("Stock information received: " + message + " from " + session.getId());
        try {
            session.getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("WebSocket error for " + session.getId() + " " + throwable.getMessage());
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("WebSocket closed for " + session.getId() + " with reason " + closeReason.getCloseCode());
        sessions.remove(session);
    }
}
