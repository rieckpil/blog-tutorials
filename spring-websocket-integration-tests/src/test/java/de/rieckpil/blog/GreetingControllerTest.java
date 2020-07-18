package de.rieckpil.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GreetingControllerTest {

  @LocalServerPort
  private Integer port;

  private BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(1);
  private BlockingQueue<Message> blockingQueueObject = new ArrayBlockingQueue(1);

  private WebSocketStompClient webSocketStompClient;
  private SimpMessagingTemplate simpMessagingTemplate;

  @BeforeEach
  public void setup() {
    this.webSocketStompClient = new WebSocketStompClient(new SockJsClient(
      List.of(new WebSocketTransport(new StandardWebSocketClient()))));
  }

  @Test
  public void testWebSockets() throws InterruptedException, ExecutionException, TimeoutException {

    webSocketStompClient.setMessageConverter(new StringMessageConverter());

    StompSession session = webSocketStompClient
      .connect(getWsPath(), new StompSessionHandlerAdapter() {
      })
      .get(1, SECONDS);

    session.subscribe("/topic/greetings", new StompFrameHandler() {

      @Override
      public Type getPayloadType(StompHeaders headers) {
        return String.class;
      }

      @Override
      public void handleFrame(StompHeaders headers, Object payload) {
        blockingQueue.offer(payload.toString());
        System.out.println("Response: " + payload);
      }
    });

    session.send("/app/hello", "Mike");

    Object payload = blockingQueue.poll(1, SECONDS);
    assertEquals("Hello, Mike!", payload);
  }

  @Test
  public void testWebSocketsWithObject() throws InterruptedException, ExecutionException, TimeoutException {

    webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());

    StompSession session = webSocketStompClient
      .connect(getWsPath(), new StompSessionHandlerAdapter() {
      })
      .get(1, SECONDS);

    session.subscribe("/topic/objects", new StompFrameHandler() {

      @Override
      public Type getPayloadType(StompHeaders headers) {
        return Message.class;
      }

      @Override
      public void handleFrame(StompHeaders headers, Object payload) {
        blockingQueueObject.offer((Message) payload);
        System.out.println("Response: " + payload);
      }
    });

    Message Message = new Message();
    Message.setMessage("Mike");
    session.send("/app/object", Message);

    Message payload = blockingQueueObject.poll(1, SECONDS);
    assertEquals("MIKE", payload.getMessage());
  }


  private String getWsPath() {
    return String.format("ws://localhost:%d/ws-endpoint", port);
  }

}

