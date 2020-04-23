package org.openjfx.passwordgenerator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Hashtable;

/**
 *
 * @author Jasper.Wieringa
 */
public class ControllerTest {

  public ControllerTest() {
  };

  @BeforeAll
  public static void setUpClass() {
  };

  @AfterAll
  public static void tearDownClass() {
  };

  @BeforeEach
  public void setUp() {
  };

  @AfterEach
  public void tearDown() {
  };

  @Test // IllegalArgumentException vanwege de testRules
  public void testPassword_wo_length_wo_rules() throws IOException {
    Password testPassword = new Password();
    PasswordLength testLength = new PasswordLength(0);
    PasswordRules testRules = new PasswordRules(new Hashtable<String, String>());

    Assertions.assertThrows(IllegalArgumentException.class,
        () -> testPassword.generatePassword(testLength.getLength(), testRules.getRules()));
  };

  @Test // IllegalArgumentException vanwege de testLength
  public void testPassword_wo_length_w_rules() throws IOException {
    Password testPassword = new Password();
    PasswordLength testLength = new PasswordLength(0);
    PasswordRules testRules = new PasswordRules(new Hashtable<String, String>());

    testRules.addRules("type", "password");
    testRules.addRules("upper", "true");
    testRules.addRules("lower", "true");
    testRules.addRules("numberic", "true");
    testRules.addRules("special", "true");

    Assertions.assertThrows(IllegalArgumentException.class,
        () -> testPassword.generatePassword(testLength.getLength(), testRules.getRules()));
  };

  @Test // IllegalArgumentException vanwege alle checkboxes als false
  public void testPassword_w_length_w_rules_false() throws IOException {
    Password testPassword = new Password();
    PasswordLength testLength = new PasswordLength(5);
    PasswordRules testRules = new PasswordRules(new Hashtable<String, String>());

    testRules.addRules("type", "PaSSwoRD");
    testRules.addRules("upper", "false");
    testRules.addRules("lower", "false");
    testRules.addRules("numberic", "false");
    testRules.addRules("special", "false");

    Assertions.assertThrows(IllegalArgumentException.class,
        () -> testPassword.generatePassword(testLength.getLength(), testRules.getRules()));
  };

  @Test // Pass
  public void testPassword_w_length_w_rules_true() throws IOException {
    Password testPassword = new Password();
    PasswordLength testLength = new PasswordLength(5);
    PasswordRules testRules = new PasswordRules(new Hashtable<String, String>());

    testRules.addRules("type", "PaSSwoRD");
    testRules.addRules("upper", "true");
    testRules.addRules("lower", "true");
    testRules.addRules("numberic", "true");
    testRules.addRules("special", "true");

    testPassword.generatePassword(testLength.getLength(), testRules.getRules());

    assertTrue(!testPassword.getPassword().isEmpty());
  };

  @Test // Pass
  public void testPassword_w_length_w_rules_true1() throws IOException {
    Password testPassword = new Password();
    PasswordLength testLength = new PasswordLength(5);
    PasswordRules testRules = new PasswordRules(new Hashtable<String, String>());

    testRules.addRules("type", "PaSSwoRD");
    testRules.addRules("upper", "false");
    testRules.addRules("lower", "true");
    testRules.addRules("numberic", "false");
    testRules.addRules("special", "false");

    testPassword.generatePassword(testLength.getLength(), testRules.getRules());

    assertTrue(!testPassword.getPassword().isEmpty());
  };
}