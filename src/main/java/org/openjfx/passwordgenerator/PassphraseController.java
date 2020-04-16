package org.openjfx.passwordgenerator;

import java.io.IOException;
import java.util.Hashtable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;

public class PassphraseController extends Controller {
  private int minLength = 3;
  private int passwordLength = minLength;
  private Hashtable<String, String> passwordRules = new Hashtable<String, String>();

  @FXML
  private Spinner<Integer> passLength = new Spinner<>(3, 20, 0, 1); // Min 3, Max 20, in stappen van 1
  @FXML
  private SpinnerValueFactory.IntegerSpinnerValueFactory limietWaarden = (SpinnerValueFactory.IntegerSpinnerValueFactory) passLength
      .getValueFactory();
  @FXML
  private TextField wordSeperator;
  @FXML
  private CheckBox capital;
  @FXML
  private CheckBox numberic;

  @FXML
  @Override
  protected void initialize() throws IOException {
    passwordRules.put("type", "passphrase");
    passwordRules.put("seperator", " ");
    passwordRules.put("capital", "true");
    passwordRules.put("numberic", "true");

    for (String type : passwordTypes.getTypes()) {
      if ((passwordRules.get("type").toLowerCase()).equals(type.toLowerCase())) {
        passwordBox.setValue(type);
        passwordTypes.getTypes().remove(type);
        break;
      }
    }

    // Genereer een wachtwoord bij het initialiseren van de controller
    passwordSetter();

    // Set de waarden in de ComboBox
    passwordBox.setItems(passwordTypes.getTypes());

    // Set de randvoorwaarden in de Spinner
    passLength.setValueFactory(limietWaarden);

    // Voeg een listener toe om de waarde van de Spinner te gebruiken
    passLength.valueProperty().addListener((observable, oldValue, newValue) -> {
      passwordLength = newValue.intValue();

      try {
        passwordSetter();
      } catch (IOException e) {
        System.out.println(e);
      }
    });
  };

  @FXML
  @Override
  protected void setState(ActionEvent event) throws IOException {
    CheckBox type = (CheckBox) event.getSource();

    // Als checkbox false is en de checkbox mag worden gewijzigd
    if (!type.isSelected() && changeAllowed(passwordRules)) {
      type.setSelected(false);

      setRules(type.getId(), type.isSelected());
      passwordSetter();
    } 
    // Als checkbox false is maar de checkbox mag niet worden gewijzigd
    else if (!type.isSelected() && !changeAllowed(passwordRules)) {
      type.setSelected(true);
    } 
    // Als checkbox true is
    else {
      setRules(type.getId(), type.isSelected());
      passwordSetter();
    }
  }

  @Override
  protected void setRules(String type, Boolean value) throws IOException {
    type = type.toLowerCase();
    String stringValue = (value == true) ? "true" : "false";

    passwordRules.replace(type, stringValue);
  };

  @FXML
  @Override
  protected void passwordSetter() throws IOException {
    generatePassword(passwordLength, passwordRules);
  };

  @FXML
  @Override
  protected void switchTo() throws IOException {
    App.setRoot("password_generator");
  };
}
