package org.openjfx.passwordgenerator;

import java.util.Hashtable;

public class PasswordRules {
  private Hashtable<String, String> passwordRules;

  protected void setRules(Hashtable<String, String> rules) {
    this.passwordRules = rules;
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