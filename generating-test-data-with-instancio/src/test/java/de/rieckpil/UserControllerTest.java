package de.rieckpil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import de.rieckpil.controller.UserController;
import de.rieckpil.controller.UserController.UserCreationRequest;
import de.rieckpil.utility.JsonUtil;
import lombok.SneakyThrows;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  @SneakyThrows
  void shouldCreateUserWithValidRequest() {
    var request = Instancio.of(UserCreationRequest.class).as(JsonUtil::convert);

    var apiPath = "/api/v1/users";
    mockMvc
        .perform(post(apiPath).contentType(MediaType.APPLICATION_JSON).content(request))
        .andExpect(status().isOk());
  }
}
