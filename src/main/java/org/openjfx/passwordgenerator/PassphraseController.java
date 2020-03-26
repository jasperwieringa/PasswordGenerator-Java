package org.openjfx.passwordgenerator;

import java.io.IOException;
import java.util.Random;
import javafx.fxml.FXML;

public class PassphraseController extends PasswordController {
  protected String controllerName = "Passphrase";

  private int wordLength;
  private String wordSeperator;
  private Boolean capitalize;
  private Boolean numberOn;

  private Random higherString = new Random();
  private Random lowerString = new Random();
  private Random number = new Random();

  @FXML
  @Override
  protected void initialize() {
    for (String type : passwordTypes.getTypes()) {
      if (this.controllerName == type) {
        passwordBox.setValue(type);
        passwordTypes.getTypes().remove(type);
        break;
      }
    }
    passwordBox.setItems(passwordTypes.getTypes());
  }

  @Override
  protected String generatePassword() throws IOException {
    return "I'm a passphrase";
  }

  @FXML
  @Override
  protected void switchTo() throws IOException {
    App.setRoot("password_generator");
  }
}
