package org.openjfx.passwordgenerator;

public class Password {
  private String password;

  protected Password(String password) {
    this.password = password;
  }

  protected void setValue(String password) {
    this.password = password;
  };

  protected String getValue() {
    return this.password;
  };
}