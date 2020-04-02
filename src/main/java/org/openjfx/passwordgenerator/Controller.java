package org.openjfx.passwordgenerator;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public abstract class Controller {
  protected Clipboard clipboard = Clipboard.getSystemClipboard();
  protected ClipboardContent content = new ClipboardContent();

  protected Password password = new Password("");
  protected PasswordTypes passwordTypes = new PasswordTypes(FXCollections.observableArrayList("Password", "Passphrase"));

  // @FXML fx:id's die in elke controller zit
  @FXML protected Label passwordLabel;
  @FXML protected Button generateButton;
  @FXML protected Button copyButton;
  @FXML protected ComboBox<String> passwordBox;

  @FXML
  protected void generatePassword() throws IOException {
    passwordLabel.setText(password.generatePassword(passwordBox.getValue()));
  }

  @FXML
  protected void copyPassword() throws IOException {
    content.putString(password.getPassword());
    clipboard.setContent(content);
  }

  // Abstracte methods
  protected abstract void toggleType() throws IOException;

  protected abstract void switchTo() throws IOException;
}