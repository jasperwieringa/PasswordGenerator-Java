package org.openjfx.passwordgenerator;

import java.util.Hashtable;

public class PasswordRules {
  private Hashtable<String, String> passwordRules;

  protected void setRules(Hashtable<String, String> rules) {
    this.passwordRules = rules;
  }

  protected void editRules(String type, Boolean value) {
    type = type.toLowerCase();
    String stringValue = (value == true) ? "true" : "false";

    this.passwordRules.replace(type, stringValue);
  }

  protected Hashtable<String, String> getRules() {
    return this.passwordRules;
  }
}