package de.rieckpil.blog;

import javax.annotation.Resource;
import javax.jms.*;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.time.Instant;

public class JmsMessageSender {

    @Resource(mappedName = "jms/JmsFactory")
    private ConnectionFactory jmsFactory;

    @Resource(mappedName = "jms/JmsQueue")
    private Queue jmsQueue;

    public void send() {

        TextMessage message;

        try (Connection connection = jmsFactory.createConnection();
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             MessageProducer producer = session.createProducer(jmsQueue)) {

            message = session.createTextMessage();
            message.setText("Hello World!");
            producer.send(message);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private String createCustomMessage() {
        CustomMessage msg = new CustomMessage("Hello World!", "Duke", Instant.now().getEpochSecond());
        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(msg).toString();
        return jsonString;
    }
}