package org.openjfx.passwordgenerator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Hashtable;

/**
 *
 * @author Jasper.Wieringa
 */
 
public class PassphraseTest {
  @Test // IllegalArgumentException vanwege ontbrekende testRules
  public void testPassphrase_wo_length_wo_rules() throws IOException {
    Password testPassphrase = new Password();
    PasswordLength testLength = new PasswordLength(0);
    PasswordRules testRules = new PasswordRules(new Hashtable<String, String>());

    Assertions.assertThrows(IllegalArgumentException.class,
        () -> testPassphrase.generatePassword(testLength.getLength(), testRules.getRules()));
  };

  @Test // IllegalArgumentException vanwege de PasswordLength
  public void testPassphrase_wo_length_w_rules() throws IOException {
    Password testPassphrase = new Password();
    PasswordLength testLength = new PasswordLength(0);
    PasswordRules testRules = new PasswordRules(new Hashtable<String, String>());

    testRules.addRules("type", "passphrase");
    testRules.addRules("seperator", "true");
    testRules.addRules("capital", "true");
    testRules.addRules("numberic", "true");

    Assertions.assertThrows(IllegalArgumentException.class,
        () -> testPassphrase.generatePassword(testLength.getLength(), testRules.getRules()));
  };

  @Test // Pass
  public void testPassphrase_w_length_w_rules_true() throws IOException {
    Password testPassphrase = new Password();
    PasswordLength testLength = new PasswordLength(5);
    PasswordRules testRules = new PasswordRules(new Hashtable<String, String>());

    testRules.addRules("type", "PASSPHRASE");
    testRules.addRules("seperator", "!@#$%^&*()");
    testRules.addRules("capital", "true");
    testRules.addRules("numberic", "true");

    testPassphrase.generatePassword(testLength.getLength(), testRules.getRules());

    assertTrue(!testPassphrase.getPassword().isEmpty());
  };

  @Test // Pass
  public void testPassphrase_w_length_w_rules_true1() throws IOException {
    Password testPassphrase = new Password();
    PasswordLength testLength = new PasswordLength(5);
    PasswordRules testRules = new PasswordRules(new Hashtable<String, String>());

    testRules.addRules("type", "pASsPhraSe");
    testRules.addRules("seperator", "true");
    testRules.addRules("capital", "true");
    testRules.addRules("numberic", "true");

    testPassphrase.generatePassword(testLength.getLength(), testRules.getRules());

    assertTrue(!testPassphrase.getPassword().isEmpty());
  };
}