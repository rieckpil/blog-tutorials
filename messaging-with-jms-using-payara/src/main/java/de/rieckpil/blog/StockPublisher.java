package de.rieckpil.blog;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonObject;

@Singleton
public class StockPublisher {

	@Resource(lookup = "jms/__defaultConnectionFactory")
	private ConnectionFactory jmsFactory;

	@Resource(lookup = "jms/stocks")
	private Queue jmsQueue;

	private String[] stockCodes = { "MSFT", "GOOGL", "AAPL", "AMZN" };

	@Schedule(second = "*/2", minute = "*", hour = "*", persistent = false)
	public void sendStockInformation() {

		TextMessage message;

		try (Connection connection = jmsFactory.createConnection();
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				MessageProducer producer = session.createProducer(jmsQueue)) {

			JsonObject stockInformation = Json.createObjectBuilder()
					.add("stockCode", stockCodes[ThreadLocalRandom.current().nextInt(stockCodes.length)])
					.add("price", ThreadLocalRandom.current().nextDouble(1.0, 150.0))
					.add("timestamp", Instant.now().toEpochMilli()).build();

			message = session.createTextMessage();
			message.setText(stockInformation.toString());

			producer.send(message);

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
