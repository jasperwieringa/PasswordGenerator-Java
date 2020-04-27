// Naam: Jasper Wieringa
// Leerlijn: Object Georienteerd programmeren
// Datum: 27-04-2020

package org.openjfx.passwordgenerator;

import java.io.IOException;

public class PasswordType {
  private String passwordType;

  protected PasswordType (String passwordType) {
    this.passwordType = passwordType;
  }

  protected String getType() throws IOException {
    return this.passwordType;
  }
}