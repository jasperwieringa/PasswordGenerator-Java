package org.openjfx.passwordgenerator;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class PasswordController extends Controller {
  private String passwordType = "Password";

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
    for (String type : passwordTypes.getTypes()) {
      if (this.passwordType == type) {
        passwordBox.setValue(type);
        passwordTypes.getTypes().remove(type);
        break;
      }
    }

    // Set de waarden in de ComboBox
    passwordBox.setItems(passwordTypes.getTypes());

    // Voeg een listener toe om de waarde van de Slider te gebruiken
    passLength.valueProperty().addListener((observable, oldValue, newValue) -> {
      setLength(newValue);
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
  }

  @FXML
  @Override
  protected void setLength(Number length) {
    passLengthLabel.setText(Integer.toString(length.intValue()));
  }

  @FXML
  @Override
  protected void switchTo() throws IOException {
    App.setRoot("passphrase_generator");
  }
}
