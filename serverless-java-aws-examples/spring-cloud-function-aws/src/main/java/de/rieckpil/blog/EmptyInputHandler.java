package de.rieckpil.blog;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class EmptyInputHandler extends SpringBootRequestHandler<Void, String> {
}
