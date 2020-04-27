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
 
public class PasswordRulesTest {
  @Test // pass
  public void testPasswordRules_add() throws IOException {
    PasswordRules passwordRules = new PasswordRules(new Hashtable<String, String>());

    passwordRules.addRules("type", "password");
    passwordRules.addRules("upper", "");
    passwordRules.addRules("lower", "");
    passwordRules.addRules("numberic", "");
    passwordRules.addRules("special", "");
    passwordRules.addRules("minLength", "");
    passwordRules.addRules("maxLength", "");

    assertTrue(!passwordRules.getRules().isEmpty());
  };

  @Test // pass
  public void testPasswordRules_edit_0() throws IOException {
    PasswordRules passwordRules = new PasswordRules(new Hashtable<String, String>());

    passwordRules.addRules("type", "password");
    passwordRules.addRules("upper", "");
    passwordRules.addRules("lower", "");
    passwordRules.addRules("numberic", "");
    passwordRules.addRules("special", "");
    passwordRules.addRules("minLength", "");
    passwordRules.addRules("maxLength", "");

    passwordRules.editRules("type", "passphrase");
    passwordRules.editRules("upper", "a");
    passwordRules.editRules("lower", "b");
    passwordRules.editRules("numberic", "c");
    passwordRules.editRules("special", "d");
    passwordRules.editRules("minLength", "e");
    passwordRules.editRules("maxLength", "f");

    assertTrue(!passwordRules.getRules().isEmpty());
  };

  @Test // Niet bestaande key in de hashtable
  public void testPasswordRules_edit_1() throws IOException {
    PasswordRules passwordRules = new PasswordRules(new Hashtable<String, String>());
    String falseRule = "seperator";

    passwordRules.addRules("type", "password");
    passwordRules.addRules("upper", "");
    passwordRules.addRules("lower", "");
    passwordRules.addRules("numberic", "");
    passwordRules.addRules("special", "");
    passwordRules.addRules("minLength", "");
    passwordRules.addRules("maxLength", "");

    passwordRules.editRules("type", "passphrase");

    Throwable exception = assertThrows(IllegalArgumentException.class,
    () -> passwordRules.editRules(falseRule, "true"));

    assertEquals("De regel van het type " + falseRule + " bestaat niet in de tabel", exception.getMessage());
  };
}