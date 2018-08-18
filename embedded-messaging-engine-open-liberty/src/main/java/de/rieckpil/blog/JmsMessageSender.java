package de.rieckpil.blog;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.time.Instant;

@Stateless
public class JmsMessageSender {

    @Resource(lookup = "jms/JmsFactory")
    private ConnectionFactory jmsFactory;

    @Resource(lookup = "jms/JmsQueue")
    private Queue jmsQueue;

    public void send() {

        TextMessage message;

        try (Connection connection = jmsFactory.createConnection();
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             MessageProducer producer = session.createProducer(jmsQueue)) {

            System.out.println("Sending a new message");
            message = session.createTextMessage();
            message.setText(createCustomMessage());
            producer.send(message);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private String createCustomMessage() {
        CustomMessage msg = new CustomMessage("Hello World!", "Duke", Instant.now().getEpochSecond());
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(msg);
    }
}