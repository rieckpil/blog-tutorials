package de.rieckpil.blog.junit4;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

import static org.junit.experimental.categories.Categories.*;

@RunWith(Categories.class)
@IncludeCategory(DatabaseTests.class)
@SuiteClasses({JUnit4ExampleTest.class, RegistrationWebTest.class})
public class DatabaseTestSuite {
}
