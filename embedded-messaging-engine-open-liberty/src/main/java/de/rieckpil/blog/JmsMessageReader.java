package de.rieckpil.blog;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@MessageDriven
public class JmsMessageReader implements MessageListener {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void onMessage(Message message) {

        TextMessage textMessage = (TextMessage) message;

        try {
            String incomingText = textMessage.getText();
            System.out.println("-- a new message arrived: " + incomingText);

            Jsonb jsonb = JsonbBuilder.create();

            CustomMessage parsedMessage = jsonb.fromJson(incomingText, CustomMessage.class);
            entityManager.persist(parsedMessage);

        } catch (JMSException e) {
            System.err.println(e.getMessage());
        }
    }
}
