package de.rieckpil.blog;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jupiter.MicroShedTest;
import org.testcontainers.shaded.com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@MicroShedTest
@SharedContainerConfig(SampleApplicationConfig.class)
public class SampleApplicationIntegrationTest {

    private static Client client;
    private static WebTarget webTarget;

    @BeforeAll
    private static void setupClient() {
        client = ClientBuilder.newBuilder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .readTimeout(2, TimeUnit.SECONDS)
                .register(JacksonJsonProvider.class)
                .build();

        webTarget = client.target(SampleApplicationConfig.app.getApplicationURL());
    }

    @Test
    public void shouldReturnSampleMessage() throws InterruptedException {

        var result = webTarget
                .path("/resources/sample")
                .request()
                .get()
                .readEntity(String.class);

        assertEquals("Hello World from MicroShed Testing", result);
    }

    /**@Test
    public void shouldCreatePerson() throws InterruptedException {

        Thread.sleep(120000);

        JsonObject person = Json.createObjectBuilder()
                .add("firstName", "duke")
                .add("lastName", "duke")
                .build();

        var result = webTarget
                .path("/resources/persons")
                .request()
                .post(Entity.entity(person, MediaType.APPLICATION_JSON));


        System.out.println(result.getStatus());
        System.out.println(result.readEntity(String.class));
        System.out.println(result.getHeaderString("Location"));
    }**/
}
