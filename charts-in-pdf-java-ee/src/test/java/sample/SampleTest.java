package sample;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SampleTest {

	@Test
	public void testLists() {
		List<String> stringList = Arrays.asList("java", "jdk", "jre");
		assertThat(stringList, hasItem("java"));
		assertThat(stringList, hasItems("java", "jdk"));
		assertThat(stringList, everyItem(containsString("j")));
	}

	@Test
	public void testWithHamcrest() {
		String result = "HelloWorld!";
		assertThat(result, is("HelloWorld!"));
	}

}