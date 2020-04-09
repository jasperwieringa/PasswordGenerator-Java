package org.openjfx.passwordgenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class Password {
  private String password;
  private int passwordLength;

  private static final String lowerString = "abcdefghijklmnopqrstuvwxyz";
  private static final String upperString = lowerString.toUpperCase();
  private static final String numberString = "0123456789";
  private static final String specialString = "!@#$%&*()_+-=[]?";

  // Password rules
  private Boolean upper;
  private Boolean lower;
  private Boolean numberic;
  private Boolean special;
  private Boolean capitalize;
  private String seperator;

  // New password
  protected Password(String password) {
    this.password = password;
  };

  // Genereer een password
  protected void generatePassword(int length, Hashtable<String, String> passwordRules) {
    String passwordString = "";
    String value = "";

    if ((passwordRules.get("type").toLowerCase()).equals("password")) {
      this.upper = (passwordRules.get("upper")).equals("true");
      this.lower = (passwordRules.get("lower")).equals("true");
      this.numberic = (passwordRules.get("numberic")).equals("true");
      this.special = (passwordRules.get("special")).equals("true");

      if (this.upper) {
        passwordString += upperString;
      }
      if (this.lower) {
        passwordString += lowerString;
      }
      if (this.numberic) {
        passwordString += numberString;
      }
      if (this.special) {
        passwordString += specialString;
      }

      String passwordStringShuffled = shufflePassword(passwordString);
      String passwordAllowed = passwordStringShuffled;

      value = generatePassword(length, passwordAllowed);
    }

    else {
      value = "Passphrase generated";
    }

    this.password = value;
  };

  // Return password (voor de copyPassword function)
  protected String getPassword() {
    return this.password;
  };

  // Genereer een random password
  protected static String generatePassword(int length, String passwordAllowed) {
    if (length < 1)
      throw new IllegalArgumentException();

    StringBuilder stringBuilder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {

      int rndCharAt = (int) (Math.random() * passwordAllowed.length());
      char rndChar = passwordAllowed.charAt(rndCharAt);

      stringBuilder.append(rndChar);

    }

    return stringBuilder.toString();

  }

  public static String shufflePassword(String string) {
      List<String> letters = Arrays.asList(string.split(""));
      Collections.shuffle(letters);
      return letters.stream().collect(Collectors.joining());
  }
}