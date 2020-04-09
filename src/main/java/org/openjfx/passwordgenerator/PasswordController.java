package org.openjfx.passwordgenerator;

import java.io.IOException;
import java.util.Hashtable;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class PasswordController extends Controller {
  private String passwordType = "Password";
  private int passwordLength = 5;
  private Hashtable<String, String> passwordRules = new Hashtable<String, String>();

  @FXML
  private Label passLengthLabel;
  @FXML
  private Slider passLength;
  @FXML
  private CheckBox upper;
  @FXML
  private CheckBox lower;
  @FXML
  private CheckBox numberic;
  @FXML
  private CheckBox special;

  @FXML
  @Override
  protected void initialize() throws IOException {
    passwordRules.put("upper", "true");
    passwordRules.put("lower", "true");
    passwordRules.put("numberic", "true");
    passwordRules.put("special", "true");

    for (String type : passwordTypes.getTypes()) {
      if (passwordType == type) {
        passwordBox.setValue(type);
        passwordTypes.getTypes().remove(type);
        break;
      }
    }

    // Genereer een wachtwoord bij het initialiseren van de controller
    passwordSetter();

    // Set de waarden in de ComboBox
    passwordBox.setItems(passwordTypes.getTypes());

    // Set de beginwaarde van de Slider
    passLength.setMin(5);
    passLength.setMax(128);
    passLength.setValue(5);
    passLengthLabel.setText("" + passLength.valueProperty().getValue().intValue() + "");

    // Voeg een listener toe om de waarde van de Slider te gebruiken
    passLength.valueProperty().addListener((observable, oldValue, newValue) -> {
      passwordLength = newValue.intValue();

      passLengthLabel.setText("" + passwordLength + "");

      try {
        passwordSetter();
      } catch (IOException e) {
        System.out.println(e);
      }
    });

    // Voeg een listener toe om de waarde van de toggle (upper) te gebruiken
    upper.selectedProperty().addListener(((observable, oldValue, newValue) -> {
      toggleType(oldValue, newValue);
    }));

    // Voeg een listener toe om de waarde van de toggle (lower) te gebruiken
    lower.selectedProperty().addListener(((observable, oldValue, newValue) -> {
      toggleType(oldValue, newValue);
    }));

    // Voeg een listener toe om de waarde van de toggle (numberic) te gebruiken
    numberic.selectedProperty().addListener(((observable, oldValue, newValue) -> {
      toggleType(oldValue, newValue);
    }));

    // Voeg een listener toe om de waarde van de toggle (special) te gebruiken
    special.selectedProperty().addListener(((observable, oldValue, newValue) -> {
      toggleType(oldValue, newValue);
    }));
  };

  @FXML
  @Override
  protected void passwordSetter() throws IOException {
    generatePassword(passwordType, passwordLength, passwordRules);
  };

  @FXML
  @Override
  protected void switchTo() throws IOException {
    App.setRoot("passphrase_generator");
  };
}
