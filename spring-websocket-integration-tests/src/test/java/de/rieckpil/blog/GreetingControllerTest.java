package de.rieckpil.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GreetingControllerTest {

  @LocalServerPort
  private Integer port;

  private WebSocketStompClient webSocketStompClient;

  @BeforeEach
  public void setup() {
    this.webSocketStompClient = new WebSocketStompClient(new SockJsClient(
      List.of(new WebSocketTransport(new StandardWebSocketClient()))));
  }

  @Test
  public void verifyGreetingIsReceived() throws Exception {

    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(1);

    webSocketStompClient.setMessageConverter(new StringMessageConverter());

    StompSession session = webSocketStompClient
      .connect(getWsPath(), new StompSessionHandlerAdapter() {})
      .get(1, SECONDS);

    session.subscribe("/topic/greetings", new StompFrameHandler() {

      @Override
      public Type getPayloadType(StompHeaders headers) {
        return String.class;
      }

      @Override
      public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println("Received message: " + payload);
        blockingQueue.add((String) payload);
      }
    });

    session.send("/app/welcome", "Mike");

    assertEquals("Hello, Mike!", blockingQueue.poll(1, SECONDS));
  }

  @Test
  public void verifyWelcomeMessageIsSent() throws Exception {
    CountDownLatch latch = new CountDownLatch(1);

    webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());

    StompSession session = webSocketStompClient
      .connect(getWsPath(), new StompSessionHandlerAdapter() {
      })
      .get(1, SECONDS);

    session.subscribe("/app/chat", new StompFrameHandler() {

      @Override
      public Type getPayloadType(StompHeaders headers) {
        return Message.class;
      }

      @Override
      public void handleFrame(StompHeaders headers, Object payload) {
        latch.countDown();
      }
    });

    if (!latch.await(1, TimeUnit.SECONDS)) {
      fail("Message not received");
    }
  }

  private String getWsPath() {
    return String.format("ws://localhost:%d/ws-endpoint", port);
  }
}

