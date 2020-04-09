package org.openjfx.passwordgenerator;

import java.util.Hashtable;
import java.util.Random;

public class Password {
  private String password;
  private int passwordLength;

  private static final String upper_string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String lower_string = "abcdefghijklmnopqrstuvwxyz";

  // Password rules
  private String seperator;
  private Boolean upper;
  private Boolean lower;
  private Boolean numberic;
  private Boolean special;
  private Boolean capitalize;

  private Random number = new Random();

  // New password
  protected Password(String password) {
    this.password = password;
  };

  // Genereer een password
  protected void generatePassword(String type, int length, Hashtable<String, String> passwordRules) {
    String value;
    this.passwordLength = length;

    if (type == "Password" || type == "password") {
      // this.upper = passwordRules.get("upper");
      // this.lower = passwordRules.get("lower");
      // this.numberic = passwordRules.get("numberic");
      // this.special = passwordRules.get("special");
      
      value = "" + number.nextInt(10) + "";
      
    } else {
      value = "This is a passphrase " + "" + number.nextInt(10) + "";
    }

    this.password = value;
  };

  // Return password (voor de copyPassword function)
  protected String getPassword() {
    return this.password;
  };

  // Genereer een random reeks aan hoofdletters
  protected static String upperString(int count) {
    StringBuilder builder = new StringBuilder();

    while (count-- != 0) {
      int character = (int) (Math.random() * upper_string.length());
      builder.append(upper_string.charAt(character));
    }
    return builder.toString();
  };

  // Genereer een random reeks aan kleine letters
  protected static String lowerString(int count) {
    StringBuilder builder = new StringBuilder();

    while (count-- != 0) {
      int character = (int) (Math.random() * upper_string.length());
      builder.append(upper_string.charAt(character));
    }
    return builder.toString();
  };
}