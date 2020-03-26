package org.openjfx.passwordgenerator;

import java.io.IOException;

public abstract class Controller {
  protected abstract void initialize();

  protected abstract String generatePassword() throws IOException;

  protected abstract void setPassword() throws IOException;

  protected abstract void copyPassword() throws IOException;

  protected abstract void switchTo() throws IOException;
}