package org.openjfx.passwordgenerator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.jupiter.api.Test;

import javafx.scene.input.ClipboardContent;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Hashtable;
import java.util.Iterator;

/**
 *
 * @author Jasper.Wieringa
 */

public class ControllerTest {
  @Test // Pass
  public void testPassword() throws IOException {
    Password password = new Password();

    assertNull(password.getPassword());
  };

  @Test // Pass
  public void passwordType() throws IOException {
    PasswordType passwordController = new PasswordType("NewType");

    assertTrue(!passwordController.getType().isEmpty());
  }

  @Test // Pass
  public void generatePassword() throws IOException {
    Password password = new Password();

    PasswordRules passwordRules = new PasswordRules(new Hashtable<String, String>());
    PasswordLength passwordLength = new PasswordLength(5);

    passwordRules.addRules("type", "PaSSwoRD");
    passwordRules.addRules("upper", "true");
    passwordRules.addRules("lower", "true");
    passwordRules.addRules("numberic", "true");
    passwordRules.addRules("special", "true");
    passwordRules.addRules("minLength", "5");
    passwordRules.addRules("maxLength", "128");

    password.generatePassword(passwordLength.getLength(), passwordRules.getRules());

    assertTrue(!password.getPassword().isEmpty());
  };

  @Test // Pass
  public void copyPassword() throws IOException {
    ClipboardContent content = new ClipboardContent();
    Password password = new Password();

    PasswordRules passwordRules = new PasswordRules(new Hashtable<String, String>());
    PasswordLength passwordLength = new PasswordLength(5);

    passwordRules.addRules("type", "PaSSwoRD");
    passwordRules.addRules("upper", "true");
    passwordRules.addRules("lower", "true");
    passwordRules.addRules("numberic", "true");
    passwordRules.addRules("special", "true");
    passwordRules.addRules("minLength", "5");
    passwordRules.addRules("maxLength", "128");

    password.generatePassword(passwordLength.getLength(), passwordRules.getRules());

    content.putString(password.getPassword());
    
    assertTrue(!content.isEmpty());
  };

  @Test // Pass, de passwordType (vanuit de rules) mag niet in de dropdown zitten
  public void setTypes_0() throws IOException {
    PasswordType passwordController = new PasswordType("Password");
    PasswordType passphraseController = new PasswordType("Passphrase");
    PasswordType passRandomController = new PasswordType("PassRandom");
    PasswordType passAnotherRandomController = new PasswordType("AnotherRandom");
    PasswordRules passwordRules = new PasswordRules(new Hashtable<String, String>());

    ObservableList<PasswordType> passwordTypes = FXCollections.observableArrayList();
    passwordTypes.add(passwordController);
    passwordTypes.add(passphraseController);
    passwordTypes.add(passRandomController);
    passwordTypes.add(passAnotherRandomController);

    ObservableList<String> dropdownTypes = FXCollections.observableArrayList();

    // Voeg PassRandom toe aan de passwordRules
    passwordRules.addRules("type", passRandomController.getType());

    Iterator<PasswordType> i = passwordTypes.iterator();
  
    while (i.hasNext()) {
      PasswordType currentType = i.next();
      String currentController = passwordRules.getRules().get("type");
      String currentValue = currentType.getType();

      if ((currentController.toLowerCase()).equals(currentValue.toLowerCase())) {
        i.remove();
      } else {
        dropdownTypes.add(currentValue);
      }
    }

    assertTrue(!dropdownTypes.contains(passwordRules.getRules().get("type")));
  }
}