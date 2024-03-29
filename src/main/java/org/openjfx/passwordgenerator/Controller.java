// Naam: Jasper Wieringa
// Leerlijn: Object Georienteerd programmeren
// Datum: 27-04-2020

package org.openjfx.passwordgenerator;

import java.io.IOException;

import java.util.Set;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import java.util.Hashtable;
import java.util.Iterator;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public abstract class Controller {
  private Clipboard clipboard = Clipboard.getSystemClipboard();
  private ClipboardContent content = new ClipboardContent();
  private Password password = new Password();
  private PasswordType passwordController = new PasswordType("Password");
  private PasswordType passphraseController = new PasswordType("Passphrase");
  private ObservableList<PasswordType> passwordTypes = FXCollections.observableArrayList();
  private ObservableList<String> dropdownTypes = FXCollections.observableArrayList();
  
  // protected vanwege de generate_password & copy_password buttons die in beide controllers gelijk zijn
  protected PasswordRules passwordRules = new PasswordRules(new Hashtable<String, String>());
  protected PasswordLength passwordLength = new PasswordLength(0);

  @FXML
  private Label passwordLabel;
  @FXML
  private Button generateButton, copyButton;
  @FXML
  protected ComboBox<String> passwordBox;

  // Genereer een wachtwoord a.h.v. de waarden vanuit de controller
  @FXML
  protected void generatePassword() throws IOException {
    password.generatePassword(passwordLength.getLength(), passwordRules.getRules());
    passwordLabel.setText(password.getPassword());
  };

  // Kopieer het wachtwoord uit de passwordLabel
  @FXML
  protected void copyPassword() throws IOException {
    content.putString(passwordLabel.getText());
    clipboard.setContent(content);
  };

  // Conversie van ObservableList<PasswordType> naar ObservableList<String>
  protected ObservableList<String> setTypes() throws IOException {
    passwordTypes.add(passwordController);
    passwordTypes.add(passphraseController);

    Iterator<PasswordType> i = passwordTypes.iterator();
  
    while (i.hasNext()) {
      PasswordType currentType = i.next();
      String currentController = passwordRules.getRules().get("type");
      String currentValue = currentType.getType();

      if ((currentController.toLowerCase()).equals(currentValue.toLowerCase())) {
        passwordBox.setValue(currentValue);
        i.remove();
      } else {
        dropdownTypes.add(currentValue);
      }
    }

    return dropdownTypes;
  };

  // Valideer of een checkbox uitgezet mag worden
  protected Boolean canChange() throws IOException {
    Boolean can_change = false;

    if (passwordRules.getRules().size() > 0) {
      Set<String> rules = passwordRules.getRules().keySet();

      int min_selected = 0;

      for (String rule : rules) {
        if (passwordRules.getRules().get(rule).equals("true")) {
          min_selected += 1;
        }
      }

      if (min_selected > 1) {
        can_change = true;
      }
    }

    return can_change;
  }

  // Abstracte methoden
  protected abstract void initialize() throws IOException; // Initializer voor elke controller

  protected abstract void setState(ActionEvent event) throws IOException; // setState voor de checkbox passwordRules
  
  protected abstract void switchTo() throws IOException; // switchTo om van controller te switchen
}