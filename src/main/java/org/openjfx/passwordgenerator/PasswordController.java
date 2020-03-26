package org.openjfx.passwordgenerator;

import java.io.IOException;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class PasswordController {
  protected String controllerName = "Password";

  private int passLength;
  private Boolean higherStringOn;
  private Boolean lowerStringOn;
  private Boolean numberOn;
  private Boolean specialOn;

  private Random higherString = new Random();
  private Random lowerString = new Random();
  private Random number = new Random();
  private Random special = new Random();

  protected Password password = new Password("");
  protected Clipboard clipboard = Clipboard.getSystemClipboard();
  protected ClipboardContent content = new ClipboardContent();

  @FXML
  protected ComboBox<String> passwordBox = new ComboBox<String>();
  protected PasswordTypes passwordTypes = new PasswordTypes(
      FXCollections.observableArrayList("Password", "Passphrase", "Hidden"));

  @FXML
  protected void initialize() {
    for (String type : passwordTypes.getTypes()) {
      if (this.controllerName == type) {
        passwordBox.setValue(type);
        passwordTypes.getTypes().remove(type);
        break;
      }
    }
    passwordBox.setItems(passwordTypes.getTypes());
  }

  protected String generatePassword() throws IOException {
    return "" + number.nextInt(6) + 1 + "";
  }

  @FXML
  protected void setPassword() throws IOException {
    password.setValue(generatePassword());
  }

  @FXML
  protected void copyPassword() throws IOException {
    content.putString(password.getValue());
    clipboard.setContent(content);
  }

  @FXML
  protected void switchTo() throws IOException {
    App.setRoot("passphrase_generator");
  }
}
