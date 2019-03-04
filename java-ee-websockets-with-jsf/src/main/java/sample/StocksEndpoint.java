package sample;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/stocks")
public class StocksEndpoint {
	private Queue<Session> queue = new ConcurrentLinkedQueue<>();

	@OnOpen
	public void openConnection(Session session) throws IOException {
		queue.add(session);
		System.out.println("connection opened+");
		session.getBasicRemote().sendText("Hello to WS");
	}

	@OnClose
	public void closedConnection(Session session) {
		queue.remove(session);
	}

	@OnError
	public void error(Session session, Throwable t) {
		queue.remove(session);
	}
}
