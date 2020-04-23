package org.openjfx.passwordgenerator;

import java.io.IOException;

public class PasswordLength {
  private int passwordLength;

  protected PasswordLength(int length) {
    this.passwordLength = length;
  }

  protected void setLength(int length) throws IOException {
    this.passwordLength = length;
  }

  protected int getLength() throws IOException {
    return this.passwordLength;
  }
}
