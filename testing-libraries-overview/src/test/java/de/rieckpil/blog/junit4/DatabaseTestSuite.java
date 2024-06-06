package de.rieckpil.blog.junit4;

import static org.junit.experimental.categories.Categories.*;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

@RunWith(Categories.class)
@IncludeCategory(DatabaseTests.class)
@SuiteClasses({JUnit4ExampleTest.class, RegistrationWebTest.class})
public class DatabaseTestSuite {}
