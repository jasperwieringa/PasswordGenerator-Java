package org.openjfx.passwordgenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileReader;

public class Password {
  private String password;

  // Wachtwoord libraries
  private static final String lowerString = "abcdefghijklmnopqrstuvwxyz";
  private static final String upperString = lowerString.toUpperCase();
  private static final String numberString = "0123456789";
  private static final String specialString = "!@#$%&*()_+-=[]?";

  // Wachtwoord regels
  private Boolean lower;
  private Boolean upper;
  private Boolean numberic;
  private Boolean special;
  private Boolean capitalize;
  private String seperator;

  protected Password(String password) {
    this.password = password;
  };

  // Genereer een wachtwoord
  protected void generatePassword(int length, Hashtable<String, String> passwordRules) {
    String newPassword = "";

    // Als het type wachtwoord een 'password' is
    if ((passwordRules.get("type").toLowerCase()).equals("password")) {
      String passwordLibrary = ""; // Lege String voor de beschikbare characters voor het wachtwoord
      String requiredString = ""; // Lege String voor de vereiste characters voor het wachtwoord

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

      // Genereer een wachtwoord van x aantal karakters - de lengte van de
      // requiredString die wordt opgebouwd uit de passwordRules
      String generatedPassword = generatePassword(length - requiredString.length(), passwordLibrary);

      // Shuffle het wachtwoord om logica te vermijden
      newPassword = shufflePassword(generatedPassword + requiredString);
    }

    else {
      // Check alle wachtwoord regels
      this.capitalize = (passwordRules.get("capital")).equals("true");
      this.numberic = (passwordRules.get("numberic")).equals("true");
      this.seperator = passwordRules.get("seperator");

      newPassword = generatePassphrase(length);
    }

    this.password = newPassword;
  };

  // Genereer een wachtwoord
  private String generatePassword(int length, String passwordString) {
    if (length < 1)
      throw new IllegalArgumentException();

    StringBuilder stringBuilder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {

      int randomCharAt = (int) (Math.random() * passwordString.length());
      char randomChar = passwordString.charAt(randomCharAt);

      stringBuilder.append(randomChar);

    }
    return stringBuilder.toString();
  }

  // Genereer een passphrase
  private String generatePassphrase(int length) {
    List<String> dictionary = new ArrayList<String>();
    Random number = new Random(System.currentTimeMillis());
    String passPhrase = "";
    String word;

    // Maak een lijst aan o.b.v. alle woorden in en.txt
    try {
      BufferedReader file = new BufferedReader(new FileReader("src/main/resources/dict/en.txt"));

      do {
        word = file.readLine();
        dictionary.add(word);
      } while (word != null);

      file.close();
    }

    catch (Exception e) {
      System.out.println(e);
    }

    // Loop over alle items in de List dictionary en genereer X aantal woorden
    for (int i = 1; i <= length; i++) {
      String randomWord = dictionary.get(number.nextInt(dictionary.size()));

      // Als uppercase vereist is
      if (capitalize) {
        randomWord = capitalize(randomWord);
      }

      passPhrase += randomWord;

      // Als een nummer vereist is
      if (numberic) {
        String randomNumber = generatePassword(1, numberString);
        passPhrase += randomNumber;
      }

      // Zolang het niet het laatste woord is, kan een seperator worden toegevoegd
      if (i != length) { 
        passPhrase += seperator;
      }
    }

    return passPhrase;
  }

  // Shuffle het wachtwoord
  private String shufflePassword(String wachtwoord) {
    List<String> letters = Arrays.asList(wachtwoord.split(""));
    Collections.shuffle(letters);

    return letters.stream().collect(Collectors.joining());
  }

  // Zet de eerste letter naar UpperCase
  private String capitalize(String str) {
    if(str == null || str.isEmpty()) {
        return str;
    }

    return str.substring(0, 1).toUpperCase() + str.substring(1);
  }

  // Return password (voor de copyPassword function)
  protected String getPassword() {
    return this.password;
  };
}