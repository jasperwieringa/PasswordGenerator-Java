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

  @FXML
  private Spinner<Integer> passLength = new Spinner<>(3, 20, 0, 1); // Min 3, Max 20, in stappen van 1
  @FXML
  private SpinnerValueFactory.IntegerSpinnerValueFactory limietWaarden = (SpinnerValueFactory.IntegerSpinnerValueFactory) passLength.getValueFactory();
  @FXML
  private TextField wordSeperator;
  @FXML
  private CheckBox capital;
  @FXML
  private CheckBox numberic;

  @FXML
  @Override
  protected void initialize() throws IOException {
    Hashtable<String, String> rules = new Hashtable<String, String>();
    rules.put("type", "passphrase");
    rules.put("seperator", wordSeperator.getText());
    rules.put("capital", "" + capital.isSelected() + "");
    rules.put("numberic", "" + numberic.isSelected() + "");

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

    // Set de randvoorwaarden in de Spinner
    passLength.setValueFactory(limietWaarden);

    // Voeg een listener toe om de waarde van de Spinner te gebruiken
    passLength.valueProperty().addListener((observable, oldValue, newValue) -> {
      passwordLength.setLength(newValue.intValue());

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

    passwordRules.editRules(type.getId(), type.isSelected());
    generatePassword();
  }

  @FXML
  @Override
  protected void switchTo() throws IOException {
    App.setRoot("password_generator");
  };
}
