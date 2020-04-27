// Naam: Jasper Wieringa
// Leerlijn: Object Georienteerd programmeren
// Datum: 27-04-2020

package org.openjfx.passwordgenerator;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;

public class PassphraseController extends Controller {
  private int minLength = 3;
  private int maxLength = 20;

  @FXML
  private Spinner<Integer> passLength = new Spinner<>(minLength, maxLength, 0, 1); // Min 3, Max 20, in stappen van 1
  @FXML
  private SpinnerValueFactory.IntegerSpinnerValueFactory limietWaarden = (SpinnerValueFactory.IntegerSpinnerValueFactory) passLength
      .getValueFactory();
  @FXML
  private TextField wordSeperator;
  @FXML
  private CheckBox capitalize;
  @FXML
  private CheckBox numberic;

  @FXML
  @Override
  protected void initialize() throws IOException {
    passwordRules.addRules("type", "passphrase");
    passwordRules.addRules("seperator", wordSeperator.getText());
    passwordRules.addRules("capitalize", "" + capitalize.isSelected());
    passwordRules.addRules("numberic", "" + numberic.isSelected());
    passwordRules.addRules("minLength", "" + minLength);
    passwordRules.addRules("maxLength", "" + maxLength);

    // Set de dropdown
    passwordBox.setItems(setTypes());

    // Set de password length
    passwordLength.setLength(minLength);

    // Set de randvoorwaarden in de Spinner
    passLength.setValueFactory(limietWaarden);

    // Voeg een listener toe om de waarde van de Spinner te gebruiken
    passLength.valueProperty().addListener((observable, oldValue, newValue) -> {
      try {
        passwordLength.setLength(newValue.intValue());
        generatePassword();
      } catch (IOException e) {
        System.out.println("Er ging iets mis bij het wijzigen van de lengte");
      }
    });

    // Voeg een listener toe om de waarde van de seperator te gebruiken
    wordSeperator.textProperty().addListener((observable, oldValue, newValue) -> {
      try {
        passwordRules.editRules("seperator", newValue);
        generatePassword();
      } catch (IOException e) {
        System.out.println("Er ging iets mis bij het wijzigen van de wachtwoord regels");
      }
    });

    // Genereer een wachtwoord bij het initialiseren van de controller
    generatePassword();
  };

  @FXML
  @Override
  protected void setState(ActionEvent event) throws IOException {
    CheckBox type = (CheckBox) event.getSource();
    passwordRules.editRules(type.getId(), "" + type.isSelected() + "");

    generatePassword();
  };

  @FXML
  @Override
  protected void switchTo() throws IOException {
    App.setRoot("password_generator");
  };
}
