package de.rieckpil.blog;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class S3EventHandler extends SpringBootRequestHandler<S3Event, Void> {
}
