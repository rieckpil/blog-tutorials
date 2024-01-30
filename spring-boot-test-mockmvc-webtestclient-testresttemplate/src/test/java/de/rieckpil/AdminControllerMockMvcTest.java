package de.rieckpil;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AdminController.class)
class AdminControllerMockMvcTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void shouldReturnAdminView() throws Exception {
    this.mockMvc
        .perform(get("/admin"))
        .andExpect(status().is(200))
        .andExpect(view().name("admin"))
        .andExpect(model().attributeExists("secretMessage"));
  }
}
