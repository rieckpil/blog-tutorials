package de.rieckpil;

import java.net.URL;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlEmailInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLInputElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Import(SecurityConfig.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerHtmlUnitTest {

  @Autowired
  private WebClient webClient;

  @BeforeEach
  void setup() {
    webClient.getOptions().setJavaScriptEnabled(false);
    webClient.getOptions().setCssEnabled(false);
  }

  @Test
  void shouldCreateAndDisplayNewCustomer() throws Exception {

    HtmlPage customerPage = webClient.getPage("/customers");

    customerPage.<HtmlTextInput>getElementByName("name").setText("duke");
    customerPage.<HtmlTextInput>getElementByName("number").setText("C0123");
    customerPage.<HtmlEmailInput>getElementByName("email").setText("duke@java.org");

    HtmlPage pageAfterFormSubmit = customerPage.getElementById("submit").click();

    assertThat(pageAfterFormSubmit.getUrl()).isEqualTo(new URL("http://localhost:8080/customers"));

    HtmlTable pendingSubscribersTable = pageAfterFormSubmit
      .getHtmlElementById("customer-list");

    List<HtmlTableRow> rows = pendingSubscribersTable.getRows();

    HtmlTableRow headerRow = rows.get(0);
    assertThat(headerRow.getCell(0).asNormalizedText()).isEqualTo("#");
    assertThat(headerRow.getCell(1).asNormalizedText()).isEqualTo("Name");
    assertThat(headerRow.getCell(2).asNormalizedText()).isEqualTo("Number");
    assertThat(headerRow.getCell(3).asNormalizedText()).isEqualTo("Email");
    assertThat(headerRow.getCell(4).asNormalizedText()).isEqualTo("Created At");

    HtmlTableRow createdRow = rows.stream().filter(
        row -> row.getCell(2).asNormalizedText().equals("C0123")
      ).findFirst()
      .get();

    assertThat(createdRow.getCell(0).asNormalizedText()).isEqualTo("1");
    assertThat(createdRow.getCell(1).asNormalizedText()).isEqualTo("duke");
    assertThat(createdRow.getCell(2).asNormalizedText()).isEqualTo("C0123");
    assertThat(createdRow.getCell(3).asNormalizedText()).isEqualTo("duke@java.org");
    assertThat(createdRow.getCell(4).asNormalizedText()).isNotBlank();
  }
}
