package de.rieckpil.blog;

public class User {
  private UserData data;

  public User() {

  }

  public User(UserData data) {
    this.data = data;
  }

  public UserData getData() {
    return data;
  }

  public void setData(UserData data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "User{" +
      "data=" + data +
      '}';
  }
}
