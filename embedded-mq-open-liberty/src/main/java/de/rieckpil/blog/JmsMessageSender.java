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

        CustomMessage msg = new CustomMessage("Hello World!", "Duke", Instant.now().getEpochSecond());

        Jsonb jsonb = JsonbBuilder.create();
        String pingJson = jsonb.toJson(msg);

        TextMessage message;
        try (Connection connection = jmsFactory.createConnection();
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             MessageProducer producer = session.createProducer(jmsQueue)) {

            JsonObject data = Json.createObjectBuilder().add("name", "Duke").add("age", 22).add("timestamp",
                    Instant.now().getEpochSecond()).build();

            String jsonString = data.toString();

            message = session.createTextMessage();
            message.setText(jsonString + "-" + pingJson);
            System.out.println("Message sent to queue: " + jsonString);
            System.out.println(producer.getDestination());
            producer.send(jmsQueue, message);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}