package de.rieckpil.learning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
// no @ExtendWith(SpringExtension.class) needed
public class ApplicationTests {

	@Test
	@DisplayName("Load the whole Spring context")
	public void contextLoads() {
	}

}
