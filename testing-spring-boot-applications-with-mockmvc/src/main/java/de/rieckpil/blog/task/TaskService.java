package de.rieckpil.blog.task;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class TaskService {

  public Long createTask(String title) {
    System.out.println("Creating a new task with title: " + title);
    return Math.abs(ThreadLocalRandom.current().nextLong());
  }

  public void deleteTask(Long id) {
    System.out.println("Deleting task with id: " + id);
  }
}
