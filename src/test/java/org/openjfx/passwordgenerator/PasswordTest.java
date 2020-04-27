package org.openjfx.passwordgenerator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Hashtable;

/**
 *
 * @author Jasper.Wieringa
 */

public class PasswordTest {
  @Test // Geen minLength & maxLength
  public void testPassword_wo_length_w_rules_0() throws IOException {
    Password testPassword = new Password();
    PasswordLength testLength = new PasswordLength(0);
    PasswordRules testRules = new PasswordRules(new Hashtable<String, String>());

    testRules.addRules("type", "password");
    testRules.addRules("upper", "true");
    testRules.addRules("lower", "true");
    testRules.addRules("numberic", "true");
    testRules.addRules("special", "true");

    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> testPassword.generatePassword(testLength.getLength(), testRules.getRules()));

    assertEquals("Je hebt geen min en/of max lengte meegegeven in de rules", exception.getMessage());
  };

  @Test // Geen maxLength
  public void testPassword_wo_length_w_rules_1() throws IOException {
    Password testPassword = new Password();
    PasswordLength testLength = new PasswordLength(0);
    PasswordRules testRules = new PasswordRules(new Hashtable<String, String>());

    testRules.addRules("type", "password");
    testRules.addRules("upper", "true");
    testRules.addRules("lower", "true");
    testRules.addRules("numberic", "true");
    testRules.addRules("special", "true");
    testRules.addRules("minLength", "3");

    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> testPassword.generatePassword(testLength.getLength(), testRules.getRules()));

    assertEquals("Je hebt geen min en/of max lengte meegegeven in de rules", exception.getMessage());
  };

  @Test // Foutieve minLength
  public void testPassword_wo_length_w_rules_2() throws IOException {
    Password testPassword = new Password();
    PasswordLength testLength = new PasswordLength(0);
    PasswordRules testRules = new PasswordRules(new Hashtable<String, String>());
    int minLength = 3;
    int maxLength = 128;

    testRules.addRules("type", "password");
    testRules.addRules("upper", "true");
    testRules.addRules("lower", "true");
    testRules.addRules("numberic", "true");
    testRules.addRules("special", "true");
    testRules.addRules("minLength", "" + minLength);
    testRules.addRules("maxLength", "" + maxLength);

    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> testPassword.generatePassword(testLength.getLength(), testRules.getRules()));

    assertEquals("Je wachtwoord moet ten minste " + minLength + " karakters lang zijn", exception.getMessage());
  };

  @Test // Foutieve maxLength
  public void testPassword_wo_length_w_rules_3() throws IOException {
    Password testPassword = new Password();
    PasswordLength testLength = new PasswordLength(130);
    PasswordRules testRules = new PasswordRules(new Hashtable<String, String>());
    int minLength = 3;
    int maxLength = 128;

    testRules.addRules("type", "password");
    testRules.addRules("upper", "true");
    testRules.addRules("lower", "true");
    testRules.addRules("numberic", "true");
    testRules.addRules("special", "true");
    testRules.addRules("minLength", "" + minLength);
    testRules.addRules("maxLength", "" + maxLength);

    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> testPassword.generatePassword(testLength.getLength(), testRules.getRules()));
  
    assertEquals("Je wachtwoord mag maximaal " + maxLength + " karakters lang zijn", exception.getMessage());
  };

  @Test // Alle checkboxes false
  public void testPassword_w_length_w_rules_false() throws IOException {
    Password testPassword = new Password();
    PasswordLength testLength = new PasswordLength(5);
    PasswordRules testRules = new PasswordRules(new Hashtable<String, String>());
    int minLength = 3;
    int maxLength = 128;

    testRules.addRules("type", "PaSSwoRD");
    testRules.addRules("upper", "false");
    testRules.addRules("lower", "false");
    testRules.addRules("numberic", "false");
    testRules.addRules("special", "false");
    testRules.addRules("minLength", "" + minLength);
    testRules.addRules("maxLength", "" + maxLength);

    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> testPassword.generatePassword(testLength.getLength(), testRules.getRules()));

    assertEquals("Er moet tenminste één checkbox zijn geselecteerd", exception.getMessage());
  };

  @Test // Pass
  public void testPassword_w_length_w_rules_true() throws IOException {
    Password testPassword = new Password();
    PasswordLength testLength = new PasswordLength(5);
    PasswordRules testRules = new PasswordRules(new Hashtable<String, String>());
    int minLength = 3;
    int maxLength = 128;

    testRules.addRules("type", "PaSSwoRD");
    testRules.addRules("upper", "true");
    testRules.addRules("lower", "true");
    testRules.addRules("numberic", "true");
    testRules.addRules("special", "true");
    testRules.addRules("minLength", "" + minLength);
    testRules.addRules("maxLength", "" + maxLength);

    testPassword.generatePassword(testLength.getLength(), testRules.getRules());

    assertTrue(!testPassword.getPassword().isEmpty());
  };

  @Test // Pass
  public void testPassword_w_length_w_rules_true1() throws IOException {
    Password testPassword = new Password();
    PasswordLength testLength = new PasswordLength(5);
    PasswordRules testRules = new PasswordRules(new Hashtable<String, String>());
    int minLength = 3;
    int maxLength = 128;

    testRules.addRules("type", "PAsSwORD");
    testRules.addRules("upper", "false");
    testRules.addRules("lower", "true");
    testRules.addRules("numberic", "false");
    testRules.addRules("special", "false");
    testRules.addRules("minLength", "" + minLength);
    testRules.addRules("maxLength", "" + maxLength);

    testPassword.generatePassword(testLength.getLength(), testRules.getRules());

    assertTrue(!testPassword.getPassword().isEmpty());
  };

}