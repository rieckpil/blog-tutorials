package de.rieckpil.blog;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReportingListener {

  @EventListener(UserCreationEvent.class)
  public void reportUserCreation(UserCreationEvent event) {
    // e.g. increment a counter to report the total amount of new users
    System.out.println("Increment counter as new user was created: " + event);
  }

  @EventListener(UserCreationEvent.class)
  public void syncUserToExternalSystem(UserCreationEvent event) {
    // e.g. send a message to a messaging queue to inform other systems
    System.out.println("informing other systems about new user: " + event);
  }
}
