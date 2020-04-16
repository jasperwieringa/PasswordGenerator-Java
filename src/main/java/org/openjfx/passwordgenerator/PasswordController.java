package org.openjfx.passwordgenerator;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class PasswordController extends Controller {
  private int minLength = 5;
  private int maxLength = 128;

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
    Hashtable<String, String> rules = new Hashtable<String, String>();
    rules.put("type", "password");
    rules.put("upper", "" + upper.isSelected() + "");
    rules.put("lower", "" + lower.isSelected() + "");
    rules.put("numberic", "" + numberic.isSelected() + "");
    rules.put("special", "" + special.isSelected() + "");

    // Set de regels
    passwordRules.setRules(rules);

    // Set de password length
    passwordLength.setLength(minLength);

    // Set de dropdown
    for (String type : passwordTypes.getTypes()) {
      if ((passwordRules.getRules().get("type").toLowerCase()).equals(type.toLowerCase())) {
        passwordBox.setValue(type);
        passwordTypes.removeType(type);
        break;
      }
    }

    passwordBox.setItems(passwordTypes.getTypes());

    // Set de beginwaarde van de Slider
    passLength.setMin(minLength);
    passLength.setMax(maxLength);
    passLength.setValue(passwordLength.getLength());
    passLengthLabel.setText("" + passLength.valueProperty().getValue().intValue() + "");

    // Voeg een listener toe om de waarde van de Slider te gebruiken
    passLength.valueProperty().addListener((observable, oldValue, newValue) -> {
      passwordLength.setLength(newValue.intValue());
      passLengthLabel.setText("" + passwordLength + "");

      // Genereer een nieuw wachtwoord
      try {
        generatePassword();
      } catch (IOException e) {
        System.out.println(e);
      }
    });

    // Genereer een wachtwoord bij het initialiseren van de controller
    generatePassword();
  };

  @FXML
  @Override
  protected void setState(ActionEvent event) throws IOException {
    CheckBox type = (CheckBox) event.getSource();

    // Als checkbox false is en de checkbox mag worden gewijzigd
    if (!type.isSelected() && changeAllowed()) {
      type.setSelected(false);

      passwordRules.editRules(type.getId(), type.isSelected());
      generatePassword();
    } 
    // Als checkbox false is maar de checkbox mag niet worden gewijzigd
    else if (!type.isSelected() && !changeAllowed()) {
      type.setSelected(true);
    } 
    // Als checkbox true is
    else {
      passwordRules.editRules(type.getId(), type.isSelected());
      generatePassword();
    }
  }

  // Controleer of de gebruiker de checkbox uit mag zetten
  private Boolean changeAllowed() {
    Boolean can_change = false;

    if (passwordRules.getRules().size() > 0) {
      Set<String> rules = passwordRules.getRules().keySet();

      int min_selected = 0;

      for (String rule : rules) {
        if (passwordRules.getRules().get(rule).equals("true")) {
          min_selected += 1;
        }
      }

      // Wanneer er op z'n minst 2 checkboxen aan staan
      if (min_selected > 1) {
        can_change = true;
      }
    }

    return can_change;
  }

  @FXML
  @Override
  protected void switchTo() throws IOException {
    App.setRoot("passphrase_generator");
  };
}
