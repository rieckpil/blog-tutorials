package de.rieckpil.blog;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RevenueReporter {

  @Scheduled(cron = "0 0 12 * * *")
  @SchedulerLock(name = "revenueReport", lockAtMostFor = "30m")
  public void report() {
    // report revenue based on e.g. orders in database
    System.out.println("Reporting revenue");
  }

  @Scheduled(cron = "0 * * * * *")
  @SchedulerLock(name = "shortRunningTask", lockAtMostFor = "50s", lockAtLeastFor = "30s")
  public void shortRunningTask() {
    System.out.println("Start short running task");
  }
}
