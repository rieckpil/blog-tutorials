package de.rieckpil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Import(SecurityConfig.class)
@WebMvcTest(AdminController.class)
class AdminControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldRedirectAnonymousUserToLogin() throws Exception {
    this.mockMvc
      .perform(get("/admin"))
      .andExpect(status().is3xxRedirection());
  }

  @Test
  @WithMockUser(username = "duke")
  void shouldAllowAccessForAuthenticatedUser() throws Exception {
    this.mockMvc
      .perform(get("/admin"))
      .andExpect(status().isOk())
      .andExpect(view().name("admin"))
      .andExpect(model().attributeExists("message"));
  }

  @Test
  void shouldAllowAccessForAuthenticatedUserAlternative() throws Exception {
    this.mockMvc
      .perform(get("/admin")
        .with(SecurityMockMvcRequestPostProcessors.user("mike")))
      .andExpect(status().isOk())
      .andExpect(view().name("admin"))
      .andExpect(model().attributeExists("message"));
  }
}
