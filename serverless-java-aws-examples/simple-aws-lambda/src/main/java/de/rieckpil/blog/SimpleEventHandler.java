package de.rieckpil.blog;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;

public class SimpleEventHandler implements RequestHandler<S3Event, Void> {
  @Override
  public Void handleRequest(S3Event input, Context context) {

    System.out.println("Bucket name:" + input.getRecords().get(0).getS3().getBucket().getName());
    System.out.println("Key name:" + input.getRecords().get(0).getS3().getObject().getKey());

    // processing

    return null;
  }
}
