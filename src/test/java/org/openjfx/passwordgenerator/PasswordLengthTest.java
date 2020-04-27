package org.openjfx.passwordgenerator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

/**
 *
 * @author Jasper.Wieringa
 */
 
public class PasswordLengthTest {
  @Test // pass
  public void testPasswordLength() throws IOException {
    PasswordLength testLength = new PasswordLength(0);
    testLength.setLength(1);

    Assertions.assertNotNull(testLength.getLength());
  };
}