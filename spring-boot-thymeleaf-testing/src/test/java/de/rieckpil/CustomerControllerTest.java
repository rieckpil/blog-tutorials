package de.rieckpil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Import(SecurityConfig.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldAllowAccessForAnonymousUser() throws Exception {
    this.mockMvc
      .perform(get("/customers"))
      .andExpect(status().isOk())
      .andExpect(view().name("customers"))
      .andExpect(model().attributeExists("customers"))
      .andExpect(model().attributeExists("customerFormObject"));
  }

  @Test
  void shouldRejectInvalidPayloadWhenCreatingCustomer() throws Exception {
    this.mockMvc
      .perform(post("/customers")
        .param("name", "duke")
        .param("number", "C0124")
        .param("email", "notanemail.io")
        .with(csrf()))
      .andExpect(status().isOk())
      .andExpect(view().name("customers"))
      .andExpect(model().hasErrors())
      .andExpect(model().attributeHasErrors("customerFormObject"));
  }

  @Test
  void shouldCreateNewCustomer() throws Exception {
    this.mockMvc
      .perform(post("/customers")
        .param("name", "duke")
        .param("number", "C0124")
        .param("email", "duke@java.io")
        .with(csrf()))
      .andExpect(status().is3xxRedirection())
      .andExpect(header().string("Location", "/customers"));
  }
}
