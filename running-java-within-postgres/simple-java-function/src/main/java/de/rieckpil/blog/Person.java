package de.rieckpil.blog;

import java.time.LocalDate;

public class Person implements Comparable<Person> {

	private Long id;
	private String firstName;
	private String lastName;
	private LocalDate dayOfBirth;

	@Override
	public int compareTo(Person o) {

		if (o.getDayOfBirth().isEqual(dayOfBirth)) {
			return 0;
		}

		if (o.getDayOfBirth().isBefore(dayOfBirth)) {
			return 1;
		}

		return -1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(LocalDate dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

}
