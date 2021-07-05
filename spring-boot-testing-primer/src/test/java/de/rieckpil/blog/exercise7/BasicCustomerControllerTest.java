package de.rieckpil.blog.exercise7;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import de.rieckpil.blog.customer.Customer;
import de.rieckpil.blog.customer.CustomerController;
import de.rieckpil.blog.customer.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class BasicCustomerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CustomerService customerService;

  @Test
  void shouldReturn200() throws Exception {
    this.mockMvc
      .perform(get("/api/customers"))
      .andExpect(status().isOk());
  }

  @Test
  void shouldReturnListOfCustomers() throws Exception {

    Customer sampleCustomer = new Customer();
    sampleCustomer.setId(42L);
    sampleCustomer.setName("duke");
    sampleCustomer.setJoinedAt(
      ZonedDateTime.of(2020, 1, 1, 0, 0, 0, 0, ZoneId.of("Europe/Berlin")));

    Mockito.when(customerService.getAllCustomers())
      .thenReturn(List.of(sampleCustomer));

    this.mockMvc
      .perform(get("/api/customers"))
      .andExpect(jsonPath("$.size()").value(1))
      .andExpect(jsonPath("$[0].id").value(42))
      .andExpect(jsonPath("$[0].name").value("duke"))
      .andExpect(jsonPath("$[0].joinedAt").value("2020-01-01T00:00:00+01:00"));
  }
}
