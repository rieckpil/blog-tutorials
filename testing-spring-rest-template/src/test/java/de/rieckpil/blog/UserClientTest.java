package de.rieckpil.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(UserClient.class)
class UserClientTest {

  @Autowired
  private UserClient userClient;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MockRestServiceServer mockRestServiceServer;

  @Test
  public void userClientSuccessfullyReturnsUser() {

    String json = """
      {
          "data": {
              "id": 1,
              "email": "janet.weaver@reqres.in",
              "first_name": "Janet",
              "last_name": "Weaver",
              "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg"
          }
      }
       """;

    this.mockRestServiceServer
      .expect(requestTo("/api/users/1"))
      .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

    User result = userClient.getSingleUser(1L);

    assertNotNull(result);
  }

  @Test
  public void userClientSuccessfullyReturnsUserDuke() throws Exception {

    String json = this.objectMapper
      .writeValueAsString(new User(new UserData(42L, "duke@java.org", "duke", "duke", "duke")));

    this.mockRestServiceServer
      .expect(requestTo("/api/users/42"))
      .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

    User result = userClient.getSingleUser(42L);

    assertEquals(42L, result.getData().getId());
    assertEquals("duke", result.getData().getFirstName());
    assertEquals("duke", result.getData().getLastName());
    assertEquals("duke", result.getData().getAvatar());
    assertEquals("duke@java.org", result.getData().getEmail());
  }

  @Test
  public void userClientThrowsExceptionWhenNoUserIsFound() {
    this.mockRestServiceServer.expect(requestTo("/api/users/1"))
      .andRespond(MockRestResponseCreators.withStatus(HttpStatus.NOT_FOUND));

    assertThrows(HttpClientErrorException.class, () -> userClient.getSingleUser(1L));
  }

}
