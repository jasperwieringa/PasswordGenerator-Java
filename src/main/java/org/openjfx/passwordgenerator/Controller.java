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
  protected PasswordTypes passwordTypes = new PasswordTypes(
      FXCollections.observableArrayList("Password", "Passphrase"));

  // @FXML
  @FXML
  protected Label passwordLabel;
  @FXML
  protected Button generateButton;
  @FXML
  protected Button copyButton;
  @FXML
  protected ComboBox<String> passwordBox;

  // Zet wachtwoord in de passwordLabel
  protected void setPassword() throws IOException {
    passwordLabel.setText(password.getPassword());
  }

  // Genereer een wachtwoord a.h.v. het object password en parse het type vanuit de passwordbox
  @FXML
  protected void generatePassword() throws IOException {
    password.generatePassword(passwordBox.getValue());
    setPassword();
  }

  // Kopieer het wachtwoord a.h.v. het object password en zet de waarde in de clipboard
  @FXML
  protected void copyPassword() throws IOException {
    content.putString(password.getPassword());
    clipboard.setContent(content);
  }

  // Toggle de aan/uit van een checkbox
  @FXML
  protected void toggleType(Boolean oldValue, Boolean newValue) {
    System.out.println("changed from " + oldValue + " to " + newValue);
  }

  // Abstracte methoden
  protected abstract void initialize() throws IOException;

  protected abstract void setLength(Number length);

  protected abstract void switchTo() throws IOException;
}