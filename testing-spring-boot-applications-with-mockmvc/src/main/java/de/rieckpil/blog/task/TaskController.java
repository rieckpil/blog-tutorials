package de.rieckpil.blog.task;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @PostMapping
  public ResponseEntity<Void> createNewTask(@RequestBody String taskTitle, UriComponentsBuilder uriComponentsBuilder) {

    Long taskId = this.taskService.createTask(taskTitle);

    return ResponseEntity
      .created(uriComponentsBuilder.path("/api/tasks/{taskId}").build(taskId))
      .build();
  }

  @DeleteMapping
  @RolesAllowed("ADMIN")
  @RequestMapping("/{taskId}")
  public void deleteTask(@PathVariable Long taskId) {
    this.taskService.deleteTask(taskId);
  }
}
