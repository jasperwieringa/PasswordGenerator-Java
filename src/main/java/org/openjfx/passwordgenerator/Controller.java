package org.openjfx.passwordgenerator;

import java.io.IOException;

import java.util.Hashtable;

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

  // Genereer een wachtwoord a.h.v. de waarden vanuit de controller
  protected void generatePassword(String type, Hashtable<String, String> passwordRules) throws IOException {
    password.generatePassword(type, passwordRules);
    passwordLabel.setText(password.getPassword());
  }

  // Kopieer het wachtwoord uit de passwordLabel
  @FXML
  protected void copyPassword() throws IOException {
    content.putString(passwordLabel.getText());
    clipboard.setContent(content);
  }

  // Toggle de aan/uit van een checkbox
  @FXML
  protected void toggleType(Boolean oldValue, Boolean newValue) {
    System.out.println("changed from " + oldValue + " to " + newValue);
  }

  // Abstracte methoden
  protected abstract void initialize() throws IOException;
  
  protected abstract void passwordSetter() throws IOException;

  protected abstract void switchTo() throws IOException;
}