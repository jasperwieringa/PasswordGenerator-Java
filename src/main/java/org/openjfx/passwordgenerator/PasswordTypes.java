package org.openjfx.passwordgenerator;

import java.io.IOException;

import javafx.collections.ObservableList;

public class PasswordTypes {
  private ObservableList<String> passwordTypes;

  protected PasswordTypes (ObservableList<String> passwordTypes) {
    this.passwordTypes = passwordTypes;
  }

  protected ObservableList<String> getTypes() throws IOException {
    return this.passwordTypes;
  }

  protected void removeType(String type) throws IOException {
    this.passwordTypes.remove(type);
  }
}