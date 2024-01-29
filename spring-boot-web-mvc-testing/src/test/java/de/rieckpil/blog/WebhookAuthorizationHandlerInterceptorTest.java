package de.rieckpil.blog;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class WebhookAuthorizationHandlerInterceptorTest {

  private WebhookAuthorizationHandlerInterceptor cut;

  private static final String VALID_TEST_API_KEY = "test400";

  @BeforeEach
  void setUp() {
    this.cut = new WebhookAuthorizationHandlerInterceptor(VALID_TEST_API_KEY);
  }

  @Test
  void shouldBlockRequestWithWhenHeaderIsMissing() throws Exception {

    HttpServletRequest httpServletRequest = new MockHttpServletRequest();
    HttpServletResponse httpServletResponse = new MockHttpServletResponse();

    boolean result = cut.preHandle(httpServletRequest, httpServletResponse, null);

    assertThat(result).isFalse();

    assertThat(httpServletResponse.getStatus()).isEqualTo(403);
  }
}
