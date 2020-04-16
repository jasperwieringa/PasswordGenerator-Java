package org.openjfx.passwordgenerator;

import javafx.collections.ObservableList;

public class PasswordTypes {
  private ObservableList<String> passwordTypes;

  protected PasswordTypes (ObservableList<String> passwordTypes) {
    this.passwordTypes = passwordTypes;
  }

  protected ObservableList<String> getTypes() {
    return this.passwordTypes;
  }

  protected void removeType(String type) {
    this.passwordTypes.remove(type);
  }
}