package de.rieckpil;

import static org.assertj.core.api.Assertions.assertThat;

import de.rieckpil.entity.User;
import de.rieckpil.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@InitializeMysqlContainer
class UserRepositoryIT {

  @Autowired private UserRepository userRepository;

  @Test
  void shouldSaveUserInDatabaseSuccessfully() {
    final var emailId = RandomString.make();
    final var password = RandomString.make();

    Boolean userExistsWithEmailId = userRepository.existsByEmailId(emailId);
    assertThat(userExistsWithEmailId).isFalse();

    final var user = new User();
    user.setEmailId(emailId);
    user.setPassword(password);

    userRepository.save(user);

    userExistsWithEmailId = userRepository.existsByEmailId(emailId);
    assertThat(userExistsWithEmailId).isTrue();
  }
}
