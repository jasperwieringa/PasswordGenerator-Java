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
    passwordRules.addRules("type", "passphrase");
    passwordRules.addRules("seperator", wordSeperator.getText());
    passwordRules.addRules("capital", "" + capital.isSelected() + "");
    passwordRules.addRules("numberic", "" + numberic.isSelected() + "");

    // Set de password length
    passwordLength.setLength(minLength);

    // Bepaal welke item(s) de dropdown krijgt
    for (String type : passwordTypes.getTypes()) {
      if ((passwordRules.getRules().get("type").toLowerCase()).equals(type.toLowerCase())) {
        passwordBox.setValue(type);
        passwordTypes.removeType(type);
        break;
      }
    }
    
    // Set de dropdown
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

    // Voeg een listener toe om de waarde van de seperator te gebruiken
    wordSeperator.textProperty().addListener((observable, oldValue, newValue) -> {
      passwordRules.editRules("seperator", newValue);

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
    passwordRules.editRules(type.getId(), "" + type.isSelected() + "");

    generatePassword();
  }

  @FXML
  @Override
  protected void switchTo() throws IOException {
    App.setRoot("password_generator");
  }
}
