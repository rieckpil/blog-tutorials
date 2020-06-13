package de.rieckpil.blog;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class IndexControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void anonymousUsersShouldNotGetSecretMessage() throws Exception {
    this.mockMvc
      .perform(get("/")
        .with(anonymous()))
      .andExpect(status().isOk())
      .andExpect(model().attributeDoesNotExist("secretMessage"))
      .andExpect(model().attribute("message", "AWS Cognito with Spring Security"));
  }

  @Test
  public void authenticatedUsersShouldGetSecretMessage() throws Exception {
    this.mockMvc
      .perform(get("/")
        .with(oidcLogin()))
      .andExpect(status().isOk())
      .andExpect(model().attributeExists("secretMessage", "message"))
      .andExpect(model().attribute("secretMessage", Matchers.stringContainsInOrder("Lorem ipsum")));
  }

  @Test
  public void authenticatedAdminUsersShouldGetDetailedSecretMessage() throws Exception {
    this.mockMvc
      .perform(get("/")
        .with(oidcLogin().authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))))
      .andExpect(status().isOk())
      .andExpect(model().attributeExists("secretMessage", "message"))
      .andExpect(model().attribute("secretMessage", "Admin message is s3crEt"));
  }

}
