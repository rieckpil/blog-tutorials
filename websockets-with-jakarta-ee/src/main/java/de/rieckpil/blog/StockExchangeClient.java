package de.rieckpil.blog;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;

@ClientEndpoint
public class StockExchangeClient {

    @OnMessage
    public void processMessageFromStockExchangeServer(String message) {
        System.out.println("Message came from the Stock Exchange server: " + message);
    }
}
