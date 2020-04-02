package org.openjfx.passwordgenerator;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class PasswordController extends Controller {
  private String passwordType = "Password";

  @FXML
  private Label passLengthLabel;
  @FXML
  private Slider passLength;

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
  }

  @FXML
  @Override
  protected void setLength(Number length) {
    passLengthLabel.setText(Integer.toString(length.intValue()));
  }

  @FXML
  @Override
  protected void toggleType() throws IOException {
    System.out.println("Setting type");
  }

  @FXML
  @Override
  protected void switchTo() throws IOException {
    App.setRoot("passphrase_generator");
  }
}
