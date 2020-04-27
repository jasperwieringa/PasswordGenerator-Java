// Naam: Jasper Wieringa
// Leerlijn: Object Georienteerd programmeren
// Datum: 27-04-2020

package org.openjfx.passwordgenerator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class PasswordTypeTest {
  @Test // pass
  public void testPasswordType() throws IOException {
    PasswordType passwordController = new PasswordType("Tester");

    assertTrue(!passwordController.getType().isEmpty());
  };
}