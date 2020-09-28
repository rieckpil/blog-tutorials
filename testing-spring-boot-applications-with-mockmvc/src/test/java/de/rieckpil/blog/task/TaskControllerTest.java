package de.rieckpil.blog.task;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TaskService taskService;

  @Test
  public void shouldRejectCreatingReviewsWhenUserIsAnonymous() throws Exception {
    this.mockMvc
      .perform(
        post("/api/tasks")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{\"taskTitle\": \"Learn MockMvc\"}")
          .with(csrf())
      )
      .andExpect(status().isUnauthorized());
  }

  @Test
  public void shouldReturnLocationOfReviewWhenUserIsAuthenticatedAndCreatesReview() throws Exception {

    when(taskService.createTask(anyString())).thenReturn(42L);

    this.mockMvc
      .perform(
        post("/api/tasks")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{\"taskTitle\": \"Learn MockMvc\"}")
          .with(csrf())
          .with(user("duke"))
      )
      .andExpect(status().isCreated())
      .andExpect(header().exists("Location"))
      .andExpect(header().string("Location", Matchers.containsString("42")));
  }

  @Test
  @WithMockUser("duke")
  public void shouldRejectDeletingReviewsWhenUserLacksAdminRole() throws Exception {
    this.mockMvc
      .perform(delete("/api/tasks/42"))
      .andExpect(status().isForbidden());
  }

  @Test
  public void shouldAllowDeletingReviewsWhenUserIsAdmin() throws Exception {
    this.mockMvc
      .perform(
        delete("/api/tasks/42")
          .with(SecurityMockMvcRequestPostProcessors.user("duke").roles("ADMIN", "SUPER_USER"))
          .with(csrf())
      )
      .andExpect(status().isOk());

    verify(taskService).deleteTask(42L);
  }
}
