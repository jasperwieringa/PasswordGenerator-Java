package org.openjfx.passwordgenerator;

import java.io.IOException;
import javafx.fxml.FXML;

public class PasswordController extends Controller {
  private String passwordType = "Password";
  
  @FXML
  @Override
  protected void initialize() throws IOException {
    for (String type : passwordTypes.getTypes()) {
      if (this.passwordType == type) {
        passwordBox.setValue(type);
        passwordTypes.getTypes().remove(type);
        break;
      }
    }

    // Set de waarden in de ComboBox
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
    App.setRoot("passphrase_generator");
  }
}
