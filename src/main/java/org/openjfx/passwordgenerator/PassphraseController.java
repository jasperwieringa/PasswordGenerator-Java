package org.openjfx.passwordgenerator;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class PassphraseController extends Controller {
  private String passwordType = "Passphrase";

  // Creer een spinner van het type Integer en zet de min/max/increase waarden
  @FXML Spinner<Integer> passLength = new Spinner<>(3, 20, 0, 1); // Min 3, Max 20, in stappen van 1
  @FXML SpinnerValueFactory.IntegerSpinnerValueFactory limietWaarden = (SpinnerValueFactory.IntegerSpinnerValueFactory) passLength.getValueFactory();
  
  @FXML
  private void initialize() {
    for (String type : passwordTypes.getTypes()) {
      if (this.passwordType == type) {
        passwordBox.setValue(type);
        passwordTypes.getTypes().remove(type);
        break;
      }
    }

    // Set de waarden in de ComboBox
    passwordBox.setItems(passwordTypes.getTypes());

    // Set de waarden in de Spinner
    passLength.setValueFactory(limietWaarden);
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
