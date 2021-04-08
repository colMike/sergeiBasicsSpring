package com.crashcourse.demo.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailsRequestModel {


  @NotNull
  @Size(min = 2, max = 10)
  private String firstName;

  @NotNull
  @Size(min = 2, max = 10)
  private String lastName;

  @NotNull
  @Email
  private String email;

  @NotNull
  @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
  private String password;

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
