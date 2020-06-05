package de.rieckpil.blog;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class SimpleLambda implements RequestHandler<String, String> {

  @Override
  public String handleRequest(String input, Context context) {
    String message = System.getenv("MESSAGE");
    return input + " " + message;
  }
}
