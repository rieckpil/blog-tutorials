package de.rieckpil.blog;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.HashMap;
import java.util.Map;

public class SimpleHttpHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
    System.out.println(input.getBody());

    APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
    responseEvent.setBody("{\"name\": \"duke\"}");
    responseEvent.setStatusCode(200);

    Map<String, String> headers = new HashMap();
    headers.put("X-Custom-Header", "Hello World from Serverless!");
    responseEvent.setHeaders(headers);

    return responseEvent;
  }
}
