package de.rieckpil.blog;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

class User {

  @NotNull
  private Long id;

  @NotEmpty
  private String name;
  private Set<String> tags;

  public User() {
  }

  public User(Long id, String name, Set<String> tags) {
    this.name = name;
    this.id = id;
    this.tags = tags;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Set<String> getTags() {
    return tags;
  }

  public void setTags(Set<String> tags) {
    this.tags = tags;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id) &&
      Objects.equals(name, user.name) &&
      Objects.equals(tags, user.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, tags);
  }
}
