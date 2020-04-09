package org.openjfx.passwordgenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class Password {
  private String password;

  // Wachtwoord libraries
  private static final String lowerString = "abcdefghijklmnopqrstuvwxyz";
  private static final String upperString = lowerString.toUpperCase();
  private static final String numberString = "0123456789";
  private static final String specialString = "!@#$%&*()_+-=[]?";

  // Wachtwoord regels
  private Boolean upper;
  private Boolean lower;
  private Boolean numberic;
  private Boolean special;
  private Boolean capitalize;
  private String seperator;

  protected Password(String password) {
    this.password = password;
  };

  // Genereer een wachtwoord
  protected void generatePassword(int length, Hashtable<String, String> passwordRules) {
    String passwordLibrary = ""; // Lege library voor de beschikbare characters voor het wachtwoord
    String requiredString = ""; // Lege library voor de vereiste characters voor het wachtwoord
    String newPassword = "";

    // Als het type wachtwoord een 'password' is
    if ((passwordRules.get("type").toLowerCase()).equals("password")) {
      // Check alle wachtwoord regels
      this.upper = (passwordRules.get("upper")).equals("true");
      this.lower = (passwordRules.get("lower")).equals("true");
      this.numberic = (passwordRules.get("numberic")).equals("true");
      this.special = (passwordRules.get("special")).equals("true");

      // Als hoofdletter vereist is
      if (this.upper) {
        requiredString += generatePassword(1, upperString);
        passwordLibrary += upperString;
      }
      // Als kleine letter vereist is
      if (this.lower) {
        requiredString += generatePassword(1, lowerString);
        passwordLibrary += lowerString;
      }
      // Als een nummer vereist is
      if (this.numberic) {
        requiredString += generatePassword(1, numberString);
        passwordLibrary += numberString;
      }
      // Als een speciaal teken vereist is
      if (this.special) {
        requiredString += generatePassword(1, specialString);
        passwordLibrary += specialString;
      }

      // Genereer een random wachtwoord en geef de lengte van het benodigde wachtwoord
      // mee (- de minimale vereiste characters) en de beschikbare characters
      String generatedPassword = generatePassword(length - requiredString.length(), passwordLibrary);

      // Shuffle het wachtwoord om logica te vermijden
      newPassword = shufflePassword(generatedPassword + requiredString);
    }

    // Anders
    else {
      newPassword = "Passphrase generated";
    }

    this.password = newPassword;
  };

  // Return password (voor de copyPassword function)
  protected String getPassword() {
    return this.password;
  };

  // Genereer een wachtwoord o.b.v. alle voorwaarden binnen de passwordAllowed
  protected static String generatePassword(int length, String passwordAllowed) {
    if (length < 1)
      throw new IllegalArgumentException();

    StringBuilder stringBuilder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {

      int randomCharAt = (int) (Math.random() * passwordAllowed.length());
      char randomChar = passwordAllowed.charAt(randomCharAt);

      stringBuilder.append(randomChar);

    }
    return stringBuilder.toString();
  }

  // Shuffle het wachtwoord
  protected static String shufflePassword(String wachtwoord) {
    List<String> letters = Arrays.asList(wachtwoord.split(""));
    Collections.shuffle(letters);

    return letters.stream().collect(Collectors.joining());
  }
}