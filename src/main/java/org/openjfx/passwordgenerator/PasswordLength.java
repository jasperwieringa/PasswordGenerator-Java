package org.openjfx.passwordgenerator;

public class PasswordLength {
  private int passwordLength;

  protected void setLength(int length) {
    this.passwordLength = length;
  }

  protected int getLength() {
    return this.passwordLength;
  }
}
