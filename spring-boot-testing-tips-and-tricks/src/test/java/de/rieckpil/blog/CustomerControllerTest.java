package de.rieckpil.blog;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureRestDocs
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @TestConfiguration
  static class ResultHandlerConfiguration {

    @Bean
    public RestDocumentationResultHandler restDocumentation() {
      return MockMvcRestDocumentation.document("{method-name}");
    }
  }

  @Test
  void allCustomers() throws Exception {
    this.mockMvc
      .perform(get("/api/customers")
        .param("amount", "42"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.size()", Matchers.equalTo(1)))
      .andExpect(jsonPath("$[0].firstName", Matchers.equalTo("Duke")))
      .andExpect(jsonPath("$[0].lastName", Matchers.equalTo("Java")))
      .andDo(document("{method-name}"));
  }

  @Test
  void createCustomer() throws Exception {
    this.mockMvc
      .perform(post("/api/customers")
        .content("{\"firstName\":\"Alice\", \"lastName\":\"Anderson\"}")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated())
      .andExpect(header().exists("Location"));
  }
}

