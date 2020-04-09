package org.openjfx.passwordgenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class PassphraseController extends Controller {
  private String passwordType = "Passphrase";
  private String passphraseLength;
  private ArrayList<Boolean> passphraseRules = new ArrayList<Boolean>(Arrays.asList(true, true));

  @FXML
  private Spinner<Integer> passLength = new Spinner<>(3, 20, 0, 1); // Min 3, Max 20, in stappen van 1
  @FXML
  private SpinnerValueFactory.IntegerSpinnerValueFactory limietWaarden = (SpinnerValueFactory.IntegerSpinnerValueFactory) passLength
      .getValueFactory();
  @FXML
  private String wordSeperator;
  @FXML
  private CheckBox capital;
  @FXML
  private CheckBox numberic;

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
      passphraseLength = Integer.toString(newValue.intValue());
      setLength();
    });

    // Voeg een listener toe om de waarde van de toggle (capital) te gebruiken
    capital.selectedProperty().addListener(((observable, oldValue, newValue) -> {
      toggleType(oldValue, newValue);
    }));

    // Voeg een listener toe om de waarde van de toggle (numberic) te gebruiken
    numberic.selectedProperty().addListener(((observable, oldValue, newValue) -> {
      toggleType(oldValue, newValue);
    }));
  }

  @FXML
  @Override
  protected void passwordSetter() throws IOException {
    generatePassword(passwordBox.getValue(), passphraseLength, passphraseRules);
  }

  @FXML
  @Override
  protected void setLength() {
    System.out.println(passphraseLength);
  }

  @FXML
  @Override
  protected void switchTo() throws IOException {
    App.setRoot("password_generator");
  }
}
