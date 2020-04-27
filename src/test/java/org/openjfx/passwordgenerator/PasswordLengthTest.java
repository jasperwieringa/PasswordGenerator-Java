// Naam: Jasper Wieringa
// Leerlijn: Object Georienteerd programmeren
// Datum: 27-04-2020

package org.openjfx.passwordgenerator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class PasswordLengthTest {
  @Test // pass
  public void testPasswordLength() throws IOException {
    PasswordLength testLength = new PasswordLength(0);
    testLength.setLength(1);

    assertNotNull(testLength.getLength());
  };
}