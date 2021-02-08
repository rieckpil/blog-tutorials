package de.rieckpil.blog;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Java15Lambda implements RequestHandler<Void, String> {

  @Override
  public String handleRequest(Void input, Context context) {

    var message = """
      Hello World!

      I'm using one of the latest language feature's of Java.
      That's cool, isn't it?

      Kind regards,
      Duke
      """;


    return message;
  }
}
