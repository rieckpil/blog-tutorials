package de.rieckpil.blog;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.StringReader;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "jms/JmsQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class JmsMessageReader implements MessageListener {

    @Override
    public void onMessage(Message message) {

        TextMessage textMessage = (TextMessage) message;

        Jsonb jsonb = JsonbBuilder.create();

        try {

            System.out.println(textMessage.getText());
            String[] result = textMessage.getText().split("-");

            System.out.println(result[0] + "\n");
            System.out.println(result[1] + "\n");

            JsonReader jsonReader = Json.createReader(new StringReader(result[0]));
            JsonObject jobj = jsonReader.readObject();
            System.out.print("Got new message on queue: " + jobj);
            System.out.println("\n");


        } catch (JMSException e) {
            System.err.println(e.getMessage());
        }

    }
}
