package de.rieckpil.blog;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ThreadLocalRandom;

@Singleton
public class StockExchangeNotifier {

    private Session session;

    @PostConstruct
    public void init() {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            this.session = container.connectToServer(StockExchangeClient.class,
                    new URI("ws://localhost:9080/stocks"));
        } catch (DeploymentException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
    public void sendNewStockExchangeInformation() {
        JsonObject stockInformation = Json.createObjectBuilder()
                .add("stock", "DKE-42")
                .add("price", new BigDecimal(ThreadLocalRandom.current().nextDouble(250.00)).setScale(2, RoundingMode.DOWN))
                .build();

        StockExchangeEndpoint.broadcastMessage(stockInformation);
    }
}
