package ${package};

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTest {

	@Test
	public void simpleJUnit5Test() {
		String result = "duke";
		assertEquals("duke", result);
	}
}
