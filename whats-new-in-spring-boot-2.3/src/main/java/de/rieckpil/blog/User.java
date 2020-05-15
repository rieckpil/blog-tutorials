package de.rieckpil.blog;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record User(String name, LocalDate dateOfBirth, boolean isRegistered) {
}

