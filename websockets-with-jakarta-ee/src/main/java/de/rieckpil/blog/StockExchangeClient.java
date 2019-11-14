package de.rieckpil.blog;

import javax.json.JsonObject;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;

@ClientEndpoint(decoders = {JSONTextDecoder.class}, encoders = {JSONTextEncoder.class})
public class StockExchangeClient {

    @OnMessage
    public void processMessageFromStockExchangeServer(JsonObject message) {
        System.out.println("Message came from the Stock Exchange server: " + message);
    }
}
