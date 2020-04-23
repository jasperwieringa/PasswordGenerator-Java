package org.openjfx.passwordgenerator;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import java.util.Hashtable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public abstract class Controller {
  private Clipboard clipboard = Clipboard.getSystemClipboard();
  private ClipboardContent content = new ClipboardContent();
  private Password password = new Password();

  @FXML
  protected Label passwordLabel;
  @FXML
  protected Button generateButton, copyButton;
  @FXML
  protected ComboBox<String> passwordBox;

  protected PasswordTypes passwordTypes = new PasswordTypes(FXCollections.observableArrayList("Password", "Passphrase"));
  protected PasswordRules passwordRules = new PasswordRules(new Hashtable<String, String>());
  protected PasswordLength passwordLength = new PasswordLength();

  // Genereer een wachtwoord a.h.v. de waarden vanuit de controller
  @FXML
  protected void generatePassword() throws IOException {
    password.generatePassword(passwordLength.getLength(), passwordRules.getRules());
    passwordLabel.setText(password.getPassword());
  }

  // Kopieer het wachtwoord uit de passwordLabel
  @FXML
  protected void copyPassword() throws IOException {
    content.putString(passwordLabel.getText());
    clipboard.setContent(content);
  }

  // Abstracte methoden
  protected abstract void initialize() throws IOException; // Initializer voor elke controller

  protected abstract void setState(ActionEvent event) throws IOException; // setState voor de checkbox passwordRules
  
  protected abstract void switchTo() throws IOException; // switchTo om van controller te switchen
}