package de.rieckpil.blog;

import de.rieckpil.blog.entity.User;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
@Startup
public class ApiBudgetRefresher {

  @PersistenceContext
  EntityManager entityManager;

  @Schedule(minute = "*", hour = "*", persistent = false)
  public void updateApiBudget() {
    System.out.println("-- refreshing API budget for all users");

    List<User> userList = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();

    for (User user : userList) {
      user.setAmountOfApiCalls(0);
    }

    System.out.println("-- successfully refreshed API budget for all users");
  }
}
