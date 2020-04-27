package org.openjfx.passwordgenerator;

import java.io.IOException;

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
    passwordRules.addRules("type", "password");
    passwordRules.addRules("upper", "" + upper.isSelected());
    passwordRules.addRules("lower", "" + lower.isSelected());
    passwordRules.addRules("numberic", "" + numberic.isSelected());
    passwordRules.addRules("special", "" + special.isSelected());
    passwordRules.addRules("minLength", "" + minLength);
    passwordRules.addRules("maxLength", "" + maxLength);

    // Set de dropdown
    passwordBox.setItems(setTypes());

    // Set de password length
    PasswordLength passwordLength = new PasswordLength(minLength);
    
    // Set de beginwaarde van de Slider
    passLength.setMin(minLength);
    passLength.setMax(maxLength);
    passLength.setValue(passwordLength.getLength());
    passLengthLabel.setText("" + minLength + "");

    // Voeg een listener toe om de waarde van de Slider te gebruiken
    passLength.valueProperty().addListener((observable, oldValue, newValue) -> {
      try {
        passwordLength.setLength(newValue.intValue());
        passLengthLabel.setText("" + passwordLength.getLength() + "");
        generatePassword();
      } catch (IOException e) {
        System.out.println("Er ging iets mis bij het wijzigen van de lengte");
      }
    });

    // Genereer een wachtwoord bij het initialiseren van de controller
    generatePassword();
  };

  @FXML
  @Override
  protected void setState(ActionEvent event) throws IOException {
    CheckBox type = (CheckBox) event.getSource();
    Boolean canChange = canChange();

    // Als checkbox false is en de checkbox mag worden gewijzigd
    if (!type.isSelected() && canChange) {
      type.setSelected(false);

      passwordRules.editRules(type.getId(), "" + type.isSelected() + "");
      generatePassword();
    }
    // Als checkbox false is maar de checkbox mag niet worden gewijzigd
    else if (!type.isSelected() && !canChange) {
      type.setSelected(true);
    }
    // Als checkbox true is
    else {
      passwordRules.editRules(type.getId(), "" + type.isSelected() + "");
      generatePassword();
    }
  };

  @FXML
  @Override
  protected void switchTo() throws IOException {
    App.setRoot("passphrase_generator");
  };

  private Boolean canChange() throws IOException {
    Boolean can_change = false;

    if (passwordRules.getRules().size() > 0) {
      Set<String> rules = passwordRules.getRules().keySet();

      int min_selected = 0;

      for (String rule : rules) {
        if (passwordRules.getRules().get(rule).equals("true")) {
          min_selected += 1;
        }
      }

      if (min_selected > 1) {
        can_change = true;
      }
    }

    return can_change;
  }
}
