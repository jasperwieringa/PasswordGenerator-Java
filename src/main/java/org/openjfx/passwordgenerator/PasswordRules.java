package org.openjfx.passwordgenerator;

import java.util.Hashtable;

public class PasswordRules {
  private Hashtable<String, String> passwordRules;

  protected PasswordRules(Hashtable<String, String> table) {
    this.passwordRules = table;
  }

  protected void addRules(String type, String value) {
    type = type.toLowerCase();
    value = value.toLowerCase();

    this.passwordRules.put(type, value);
  }

  protected void editRules(String type, String value) {
    type = type.toLowerCase();
    value = value.toLowerCase();

    this.passwordRules.replace(type, value);
  }

  protected Hashtable<String, String> getRules() {
    return this.passwordRules;
  }
}