package de.rieckpil.blog;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jupiter.MicroShedTest;
import org.mockserver.client.MockServerClient;
import org.testcontainers.shaded.com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import javax.json.Json;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;

import static de.rieckpil.blog.SampleApplicationConfig.mockServer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@MicroShedTest
@SharedContainerConfig(SampleApplicationConfig.class)
public class SampleResourceIntegrationTest {

    private static Client client;
    private static WebTarget sampleResourceTarget;

    @BeforeAll
    private static void setupClient() {
        client = ClientBuilder.newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .register(JacksonJsonProvider.class)
                .build();

        sampleResourceTarget = client.target(SampleApplicationConfig.app.getApplicationURL() + "/resources/sample");
    }

    @Test
    public void shouldReturnSampleMessage() {
        var result = sampleResourceTarget
                .path("/message")
                .request(MediaType.TEXT_PLAIN)
                .get()
                .readEntity(String.class);

        assertEquals("Hello World from MicroShed Testing", result);
    }

    @Test
    public void shouldReturnQuoteOfTheDay() {

        var resultQuote = Json.createObjectBuilder()
                .add("contents",
                        Json.createObjectBuilder().add("quotes",
                                Json.createArrayBuilder().add(Json.createObjectBuilder()
                                        .add("quote", "Do not worry if you have built your castles in the air. " +
                                                "They are where they should be. Now put the foundations under them."))))
                .build();

        new MockServerClient(mockServer.getContainerIpAddress(), mockServer.getServerPort())
                .when(request("/qod"))
                .respond(response().withBody(resultQuote.toString(), com.google.common.net.MediaType.JSON_UTF_8));

        var result = sampleResourceTarget
                .path("/quotes")
                .request(MediaType.TEXT_PLAIN)
                .get()
                .readEntity(String.class);

        System.out.println("Quote of the day: " + result);

        assertNotNull(result);
        assertEquals(false, result.isEmpty());
    }
}
