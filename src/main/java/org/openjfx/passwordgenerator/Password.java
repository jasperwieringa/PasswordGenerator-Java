package org.openjfx.passwordgenerator;

import java.util.Random;

public class Password {
  private String password;

  // Password rules
  private String wordSeperator;
  private int passLength;
  private Boolean higherStringOn;
  private Boolean lowerStringOn;
  private Boolean numberOn;
  private Boolean specialOn;
  private Boolean capitalize; 

  private Random higherString = new Random();
  private Random lowerString = new Random();
  private Random number = new Random();
  private Random special = new Random();
    
  // New password
  protected Password(String password) {
    this.password = password;
  }

  // Genereer een password
  protected String generatePassword(String type) {
    String value;

    if (type == "Password" || type == "password") {
      value = "" + number.nextInt(6) + 1 + "";
    }
    else if (type == "Passphrase" || type == "passphrase") {
      value = "This is a passphrase";
    }
    else {
      value = "Completely random value";
    }

    return value;
  }

  // Return password (voor de copyPassword function)
  protected String getPassword() {
    return this.password;
  };
}