package de.rieckpil;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

class InstructorCoursesTest {

  @Test
  void livingUnderARock() {
    // Arrange
    Course course = new Course();
    course.setName("test-course");
    course.setDescription("description-of-test-course");
    course.setDuration(Duration.ofHours(15));

    Course anotherCourse = new Course();
    anotherCourse.setName("another-test-course");
    anotherCourse.setDescription("description-of-another-test-course");
    anotherCourse.setDuration(Duration.ofDays(2));

    List<Course> courses = List.of(course, anotherCourse);

    Instructor instructor = new Instructor();
    instructor.setFirstName("test-first-name");
    instructor.setLastName("test-last-name");
    instructor.setEmailId("test-email-id");
    instructor.setCategory("test-category");
    instructor.setCourses(courses);

    // Act
    int totalCourses = instructor.getCourses().size();

    // Assert
    assertThat(totalCourses).isEqualTo(2);
  }

  @Test
  void livingInTheKnow() {
    // Arrange
    Instructor instructor = Instancio.of(Instructor.class).create();

    // Act
    int totalCourses = instructor.getCourses().size();

    // Assert
    assertThat(totalCourses).isGreaterThan(0);
  }

  @Getter
  @Setter
  static class Course {

    private String name;
    private String description;
    private Duration duration;
  }

  @Getter
  @Setter
  static class Instructor {

    private String firstName;
    private String lastName;
    private String emailId;
    private String category;
    private List<Course> courses;
  }
}
