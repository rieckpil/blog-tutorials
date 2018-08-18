package de.rieckpil.blog;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class JmsWriterTimer {

    @Inject
    JmsMessageSender sender;

    @Schedule(second = "*/2", minute = "*", hour = "*", persistent = false)
    public void sendJmsMessage() {
        sender.send();
    }
}
