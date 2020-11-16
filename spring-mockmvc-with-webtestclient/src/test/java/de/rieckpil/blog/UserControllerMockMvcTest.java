package de.rieckpil.blog;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerMockMvcTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Test
  void shouldForbidAccessToUnauthenticatedRequests() throws Exception {
    this.mockMvc
      .perform(MockMvcRequestBuilders.get("/api/users"))
      .andExpect(status().is4xxClientError());
  }

  @Test
  @WithMockUser(username = "duke")
  void shouldReturnListOfUsersForAuthenticatedRequests() throws Exception {

    when(userService.getAllUsers())
      .thenReturn(List.of(new User(42L, "duke"), new User(24L, "mike")));

    this.mockMvc
      .perform(MockMvcRequestBuilders.get("/api/users"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.size()", Matchers.is(2)));
  }
}
