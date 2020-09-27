package de.rieckpil.blog.dashboard;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DashboardController.class)
class DashboardControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private DashboardService dashboardService;

  @Test
  public void shouldReturnViewWithPrefilledData() throws Exception {
    when(dashboardService.getAnalyticsGraphData()).thenReturn(new Integer[]{13, 42});

    this.mockMvc
      .perform(get("/dashboard"))
      .andExpect(status().isOk())
      .andExpect(view().name("dashboard"))
      .andExpect(model().attribute("user", "Duke"))
      .andExpect(model().attribute("analyticsGraph", Matchers.arrayContaining(13, 42)))
      .andExpect(model().attributeExists("quickNote"));
  }
}
