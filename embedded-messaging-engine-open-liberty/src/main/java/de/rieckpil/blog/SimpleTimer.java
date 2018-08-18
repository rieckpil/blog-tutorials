package de.rieckpil.blog;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class SimpleTimer {

    @Inject
    private JmsMessageSender sender;

    @PersistenceContext
    private EntityManager entityManager;

    @Schedule(second = "*/2", minute = "*", hour = "*", persistent = false)
    public void sendJmsMessage() {
        sender.send();
    }

    @Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
    public void checkAmountOfMessage() {
        System.out.println(entityManager.createQuery("SELECT m FROM CustomMessage m").getResultList().size() +
                " messages are stored in the database");
    }
}
