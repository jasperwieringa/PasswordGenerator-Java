package org.openjfx.passwordgenerator;

import java.io.IOException;
import javafx.fxml.FXML;

public class PassphraseController extends Controller {
  private String passwordType = "Passphrase";

  @FXML
  private void initialize() {
    for (String type : passwordTypes.getTypes()) {
      if (this.passwordType == type) {
        passwordBox.setValue(type);
        passwordTypes.getTypes().remove(type);
        break;
      }
    }
    passwordBox.setItems(passwordTypes.getTypes());
  }

  @FXML
  @Override
  protected void toggleType() throws IOException {
    System.out.println("Hi");
  }

  @FXML
  @Override
  protected void switchTo() throws IOException {
    App.setRoot("password_generator");
  }
}
