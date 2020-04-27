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
import java.io.IOException;

public class Password {
  private String password;
  private String passwordType;

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

  // Wachtwoord setter
  protected void generatePassword(int length, Hashtable<String, String> passwordRules) throws IOException {
    
    // Valideer de regels
    if (validateRules(passwordRules)) {

      // Als het type wachtwoord een 'password' is
      if (this.passwordType.equals("password")) {
        String passwordLibrary = ""; // Lege String voor de beschikbare characters voor het wachtwoord
        String requiredString = ""; // Lege String voor de vereiste characters voor het wachtwoord

        // Als kleine letter vereist is
        if (this.lower) {
          requiredString += generatePassword(1, lowerString);
          passwordLibrary += lowerString;
        }
        // Als hoofdletter vereist is
        if (this.upper) {
          requiredString += generatePassword(1, upperString);
          passwordLibrary += upperString;
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

        if (requiredString.length() < length) {
          if (requiredString.length() > 0) {
            // Genereer een wachtwoord van x aantal karakters - de lengte van de requiredString
            String generatedPassword = generatePassword(length - requiredString.length(), passwordLibrary);

            // Shuffle het wachtwoord om logica te vermijden
            this.password = shufflePassword(generatedPassword + requiredString);
          } else {
            throw new IllegalArgumentException("Er moet tenminste één checkbox zijn geselecteerd");
          }
        } else {
          throw new IllegalArgumentException(
              "Je wachtwoord moet ten minste " + requiredString.length() + " karakters lang zijn");
        }
      }
      else {
        if (length > 0) {
          this.password = generatePassphrase(length);
        } else {
          throw new IllegalArgumentException(
              "Je wachtwoord moet ten minste 1 karakter lang zijn");
        }
      }
    } else {
      throw new IllegalArgumentException("Wachtwoordtype ontbreekt in de passwordRules");
    }
  };

  // Wachtwoord getter
  protected String getPassword() throws IOException {
    return this.password;
  };

  // Valideer de wachtwoord regels
  private Boolean validateRules(Hashtable<String, String> passwordRules) {
    this.passwordType = passwordRules.get("type");

    if (this.passwordType != null) {
      if (this.passwordType.toLowerCase().equals("password")) {
        // Check alle password regels
        this.lower = passwordRules.get("lower") != null ? passwordRules.get("lower").equals("true") : false;
        this.upper = passwordRules.get("upper") != null ? passwordRules.get("upper").equals("true") : false;
        this.numberic = passwordRules.get("numberic") != null ? passwordRules.get("upper").equals("numberic") : false;
        this.special = passwordRules.get("special") != null ? passwordRules.get("upper").equals("special") : false;
      } 
      else {
        // Check alle passphrase regels
        this.capitalize = passwordRules.get("capitalize") != null ? passwordRules.get("capitalize").equals("special") : false;
        this.numberic = passwordRules.get("numberic") != null ? passwordRules.get("numberic").equals("special") : false;
        this.seperator = passwordRules.get("seperator").equals("") ? " " : passwordRules.get("seperator");
      }
      return true;
    } else {
      return false;
    }
  }

  // Genereer een wachtwoord
  private String generatePassword(int length, String passwordString) throws IOException {
    StringBuilder stringBuilder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {

      int randomCharAt = (int) (Math.random() * passwordString.length());
      char randomChar = passwordString.charAt(randomCharAt);

      stringBuilder.append(randomChar);

    }
    return stringBuilder.toString();
  }

  // Genereer een passphrase
  private String generatePassphrase(int length) throws IOException {
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
    } catch (Exception e) {
      System.out.println(e);
    }

    // Loop over alle items in de List dictionary en genereer X aantal woorden
    for (int i = 1; i <= length; i++) {
      String randomWord = dictionary.get(number.nextInt(dictionary.size()));

      // Als uppercase vereist is
      if (this.capitalize) {
        randomWord = capitalize(randomWord);
      }

      passPhrase += randomWord;

      // Als een nummer vereist is
      if (this.numberic) {
        String randomNumber = generatePassword(1, numberString);
        passPhrase += randomNumber;
      }

      // Zolang het niet het laatste woord is, kan een seperator worden toegevoegd
      if (i != length) {
        passPhrase += this.seperator;
      }
    }

    return passPhrase;
  }

  // Shuffle het wachtwoord
  private String shufflePassword(String wachtwoord) throws IOException {
    List<String> letters = Arrays.asList(wachtwoord.split(""));
    Collections.shuffle(letters);

    return letters.stream().collect(Collectors.joining());
  }

  // Zet de eerste letter naar UpperCase
  private String capitalize(String str) throws IOException {
    if (str == null || str.isEmpty()) {
      return str;
    }

    return str.substring(0, 1).toUpperCase() + str.substring(1);
  }
}