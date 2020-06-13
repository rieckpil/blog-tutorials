package de.rieckpil.blog;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FullControlLambda implements RequestStreamHandler {

  @Override
  public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Person person = objectMapper.readValue(input, Person.class);
    System.out.println("Parsed person: " + person);
    person.setId(UUID.randomUUID().toString());
    objectMapper.writeValue(output, person);
  }
}
