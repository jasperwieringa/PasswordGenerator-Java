// Naam: Jasper Wieringa
// Leerlijn: Object Georienteerd programmeren
// Datum: 27-04-2020

package org.openjfx.passwordgenerator;

import java.io.IOException;

import java.util.Hashtable;

public class PasswordRules {
  private Hashtable<String, String> passwordRules;

  protected PasswordRules(Hashtable<String, String> table) {
    this.passwordRules = table;
  }

  protected void addRules(String type, String value) throws IOException {
    type = type.toLowerCase();
    value = value.toLowerCase();

    this.passwordRules.put(type, value);
  }

  protected void editRules(String type, String value) throws IOException {
    type = type.toLowerCase();
    value = value.toLowerCase();

    if (this.passwordRules.containsKey(type)) {
      this.passwordRules.replace(type, value);
    } else {
      throw new IllegalArgumentException("De regel van het type " + type + " bestaat niet in de tabel");
    }
  }

  protected Hashtable<String, String> getRules() throws IOException {
    return this.passwordRules;
  }
}