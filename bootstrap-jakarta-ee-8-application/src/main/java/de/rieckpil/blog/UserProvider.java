package de.rieckpil.blog;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.json.JsonArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;

public class UserProvider {

    private WebTarget webTarget;
    private Client client;

    @PostConstruct
    public void init() {
        this.client = ClientBuilder
                .newBuilder()
                .readTimeout(2, TimeUnit.SECONDS)
                .connectTimeout(2, TimeUnit.SECONDS)
                .build();

        this.webTarget = this.client.target("https://jsonplaceholder.typicode.com/users");
    }

    public JsonArray getAllUsers() {
        return this.webTarget
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get()
                .readEntity(JsonArray.class);
    }

    @PreDestroy
    public void tearDown() {
        this.client.close();
    }
}
