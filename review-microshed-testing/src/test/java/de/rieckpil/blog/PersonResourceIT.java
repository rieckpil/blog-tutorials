package de.rieckpil.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;

@MicroShedTest
@SharedContainerConfig(SampleApplicationConfig.class)
public class PersonResourceIT {
    
    @RESTClient
    public static PersonResource personsEndpoint;
    
    @Test
    public void shouldCreatePerson() {
        Person duke = new Person();
        duke.setFirstName("duke");
        duke.setLastName("jakarta");
        
        Response result = personsEndpoint.createNewPerson(null, duke);

        assertEquals(Response.Status.CREATED.getStatusCode(), result.getStatus());
        var createdUrl = result.getHeaderString("Location");
        assertNotNull(createdUrl);

        var id = Long.valueOf(createdUrl.substring(createdUrl.lastIndexOf('/') + 1));
        assertTrue(id > 0, "Generated ID should be greater than 0 but was: " + id);
        
        var newPerson = personsEndpoint.getPersonById(id);
        assertNotNull(newPerson);
        assertEquals("duke", newPerson.getFirstName());
        assertEquals("jakarta", newPerson.getLastName());
    }
}
