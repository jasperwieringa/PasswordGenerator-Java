package org.openjfx.passwordgenerator;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class PassphraseController extends Controller {
  private String passwordType = "Passphrase";

  @FXML
  private Spinner<Integer> passLength = new Spinner<>(3, 20, 0, 1); // Min 3, Max 20, in stappen van 1
  @FXML
  private SpinnerValueFactory.IntegerSpinnerValueFactory limietWaarden = (SpinnerValueFactory.IntegerSpinnerValueFactory) passLength.getValueFactory();

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

    // Set de randvoorwaarden in de Spinner
    passLength.setValueFactory(limietWaarden);

    // Voeg een listener toe om de waarde van de Spinner te gebruiken
    passLength.valueProperty().addListener((observable, oldValue, newValue) -> {
      setLength(newValue);
    });

  }

  @FXML
  @Override
  protected void setLength(Number length) {
    System.out.println(length);
  }

  @FXML
  @Override
  protected void toggleType() throws IOException {
    System.out.println("Setting type");
  }

  @FXML
  @Override
  protected void switchTo() throws IOException {
    App.setRoot("password_generator");
  }
}
