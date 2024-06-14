package de.rieckpil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

class SecretKeyInitializer implements BeforeAllCallback {

  private static final AtomicBoolean INITIAL_INVOCATION = new AtomicBoolean(Boolean.TRUE);

  @Override
  public void beforeAll(ExtensionContext context) {
    if (INITIAL_INVOCATION.getAndSet(Boolean.FALSE)) {
      var secretKey = Jwts.SIG.HS256.key().build().getEncoded();
      var base64EncodedSecretKey = Encoders.BASE64.encode(secretKey);
      System.setProperty("de.rieckpil.jwt.secret-key", base64EncodedSecretKey);
    }
  }
}
