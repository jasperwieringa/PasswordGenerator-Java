package org.openjfx.passwordgenerator;

import java.io.IOException;
import java.util.Hashtable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class PasswordController extends Controller {
  private int minLength = 5;
  private int maxLength = 128;
  private int passwordLength = minLength;
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
    passwordRules.put("type", "password");
    passwordRules.put("upper", "" + upper.isSelected() + "");
    passwordRules.put("lower", "" + lower.isSelected() + "");
    passwordRules.put("numberic", "" + numberic.isSelected() + "");
    passwordRules.put("special", "" + special.isSelected() + "");

    for (String type : passwordTypes) {
      if ((passwordRules.get("type").toLowerCase()).equals(type.toLowerCase())) {
        passwordBox.setValue(type);
        passwordTypes.remove(type);
        break;
      }
    }

    // Genereer een wachtwoord bij het initialiseren van de controller
    passwordSetter();

    // Set de waarden in de ComboBox
    passwordBox.setItems(passwordTypes);

    // Set de beginwaarde van de Slider
    passLength.setMin(minLength);
    passLength.setMax(maxLength);
    passLength.setValue(passwordLength);
    passLengthLabel.setText("" + passLength.valueProperty().getValue().intValue() + "");

    // Voeg een listener toe om de waarde van de Slider te gebruiken
    passLength.valueProperty().addListener((observable, oldValue, newValue) -> {
      passwordLength = newValue.intValue();
      passLengthLabel.setText("" + passwordLength + "");

      // Genereer een nieuw wachtwoord
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
    App.setRoot("passphrase_generator");
  };
}
