package de.rieckpil.blog;

import java.io.StringReader;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@MessageDriven(name = "stockmdb", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/stocks"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class StockListener implements MessageListener {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void onMessage(Message message) {

		TextMessage textMessage = (TextMessage) message;

		try {
			System.out.println("A new stock information arrived: " + textMessage.getText());

			JsonReader jsonReader = Json.createReader(new StringReader(textMessage.getText()));
			JsonObject stockInformation = jsonReader.readObject();

			em.persist(new StockHistory(stockInformation));
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
