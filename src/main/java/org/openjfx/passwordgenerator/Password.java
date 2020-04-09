package org.openjfx.passwordgenerator;

import java.util.ArrayList;
import java.util.Random;

public class Password {
  private String password;

  // Password rules
  private int passLength;
  private String wordSeperator;
  private Boolean upperOn;
  private Boolean lowerOn;
  private Boolean numbericOn;
  private Boolean specialOn;
  private Boolean capitalizeOn;

  private Random number = new Random();

  // New password
  protected Password(String password) {
    this.password = password;
  }

  // Genereer een password
  protected void generatePassword(String type, String length, ArrayList<Boolean> passwordRules) {
    String value;

    if (type == "Password" || type == "password") {
      value = "" + number.nextInt(10) + "";
    } else if (type == "Passphrase" || type == "passphrase") {
      value = "This is a passphrase";
    } else {
      value = "Completely random value";
    }

    this.password = value;
  }

  // Return password (voor de copyPassword function)
  protected String getPassword() {
    return this.password;
  };
}