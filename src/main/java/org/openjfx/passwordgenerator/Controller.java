package org.openjfx.passwordgenerator;

import java.io.IOException;

import java.util.Hashtable;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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

  @FXML
  protected Label passwordLabel;
  @FXML
  protected Button generateButton;
  @FXML
  protected Button copyButton;
  @FXML
  protected ComboBox<String> passwordBox;

  // Genereer een wachtwoord a.h.v. de waarden vanuit de controller
  protected void generatePassword(int passwordLength, Hashtable<String, String> passwordRules) throws IOException {
    password.generatePassword(passwordLength, passwordRules);
    passwordLabel.setText(password.getPassword());
  }

  // Kopieer het wachtwoord uit de passwordLabel
  @FXML
  protected void copyPassword() throws IOException {
    content.putString(passwordLabel.getText());
    clipboard.setContent(content);
  }

  // Controleer of de gebruiker de checkbox uit mag zetten
  protected Boolean changeAllowed(Hashtable<String, String> passwordRules) {
    Boolean can_change = false;

    if (passwordRules.size() > 0) {
      Set<String> rules = passwordRules.keySet();

      int min_selected = 0;

      for (String rule : rules) {
        if (passwordRules.get(rule).equals("true")) {
          min_selected += 1;
        }
      }

      // Wanneer er op z'n minst 2 checkboxen aan staan
      if (min_selected > 1) {
        can_change = true;
      }
    }

    return can_change;
  };

  // Abstracte methoden
  protected abstract void initialize() throws IOException; // Initializer voor elke controller

  protected abstract void setState(ActionEvent event) throws IOException; // setState voor de checkbox passwordRules

  protected abstract void setRules(String type, Boolean value) throws IOException; // setRules om de passwordRules bij te werken

  protected abstract void passwordSetter() throws IOException; // passwordSetter om generatePassword vanuit de controller op te roepen

  protected abstract void switchTo() throws IOException; // switchTo om van controller te switchen
}