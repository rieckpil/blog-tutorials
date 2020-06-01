package de.rieckpil.blog;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDate;

public class Person {

  private String id;
  private String name;
  private LocalDate dayOfBirth;

  @JsonCreator
  public Person(String id, String name, LocalDate dayOfBirth) {
    this.id = id;
    this.name = name;
    this.dayOfBirth = dayOfBirth;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getDayOfBirth() {
    return dayOfBirth;
  }

  public void setDayOfBirth(LocalDate dayOfBirth) {
    this.dayOfBirth = dayOfBirth;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Person{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", dayOfBirth=" + dayOfBirth +
      '}';
  }
}
