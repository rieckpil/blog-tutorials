package de.rieckpil.blog;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jupiter.MicroShedTest;
import org.testcontainers.shaded.com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import javax.json.Json;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@MicroShedTest
@SharedContainerConfig(SampleApplicationConfig.class)
public class PersonResourceIntegrationTest {

    private static Client client;
    private static WebTarget personResourceTarget;

    @BeforeAll
    private static void setupClient() {
        client = ClientBuilder.newBuilder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .readTimeout(2, TimeUnit.SECONDS)
                .register(JacksonJsonProvider.class)
                .build();

        personResourceTarget = client.target(SampleApplicationConfig.app.getApplicationURL() + "/resources/persons");
    }

    @Test
    public void shouldCreatePersonAndFindIt() {

        var person = Json.createObjectBuilder()
                .add("firstName", "duke")
                .add("lastName", "jakarta")
                .build()
                .toString();

        var result = personResourceTarget
                .request()
                .post(Entity.entity(person, MediaType.APPLICATION_JSON));

        assertEquals(Response.Status.CREATED.getStatusCode(), result.getStatus());
        assertNotNull(result.getHeaderString("Location"));

        var apiResult = client
                .target(result.getHeaderString("Location"))
                .request(MediaType.APPLICATION_JSON)
                .get()
                .readEntity(String.class);

        var newPerson = Json.createReader(new StringReader(apiResult)).readObject();

        assertNotNull(newPerson);
        assertEquals("duke", newPerson.getString("firstName"));
        assertEquals("jakarta", newPerson.getString("lastName"));
    }
}
