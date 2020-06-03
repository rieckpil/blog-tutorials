package de.rieckpil.blog;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class FunctionConfiguration {

  private static Logger logger = LoggerFactory.getLogger(Application.class);

  @Bean
  public Function<String, String> uppercase() {
    return value -> {
      logger.info("Processing uppercase for String '{}'", value);
      return value.toUpperCase();
    };
  }

  @Bean
  public Supplier<String> randomString() {
    return () -> UUID.randomUUID().toString();
  }

  @Bean
  public Consumer<S3Event> processS3Event() {
    return s3Event -> {
      String bucket = s3Event.getRecords().get(0).getS3().getBucket().getName();
      String key = s3Event.getRecords().get(0).getS3().getObject().getKey();

      logger.info("Something was uploaded to S3: " + bucket + "/" + key);

      // ... further processing of the S3Ev ent
    };
  }

@Bean
public Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> processXmlOrder() {
  return value -> {
    try {
      ObjectMapper objectMapper = new XmlMapper();
      Order order = objectMapper.readValue(value.getBody(), Order.class);
      logger.info("Successfully deserialized XML order '{}'", order);

      // ... processing Order
      order.setProcessed(true);

      APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
      responseEvent.setStatusCode(201);
      responseEvent.setHeaders(Map.of("Content-Type", "application/xml"));
      responseEvent.setBody(objectMapper.writeValueAsString(order));
      return responseEvent;
    } catch (IOException e) {
      e.printStackTrace();
      return new APIGatewayProxyResponseEvent().withStatusCode(500);
    }
  };
}

  @Bean
  public Function<Message<Person>, Message<Person>> processPerson() {
    return value -> {
      Person person = value.getPayload();
      logger.info("Processing incoming person '{}'", person);

      // ... storing Person in database
      person.setId(UUID.randomUUID().toString());
      logger.info("Successfully stored person in database with id '{}'", person.getId());

      Map<String, Object> resultHeader = new HashMap();
      resultHeader.put("statuscode", HttpStatus.CREATED.value());
      resultHeader.put("X-Custom-Header", "Hello World from Spring Cloud Function AWS Adapter");
      return new GenericMessage(person, resultHeader);
    };
  }

}
