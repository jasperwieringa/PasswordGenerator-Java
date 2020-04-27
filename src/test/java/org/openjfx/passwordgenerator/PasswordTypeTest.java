package org.openjfx.passwordgenerator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

/**
 *
 * @author Jasper.Wieringa
 */
 
public class PasswordTypeTest {
  @Test // pass
  public void testPasswordType() throws IOException {
    PasswordType passwordController = new PasswordType("Tester");

    assertTrue(!passwordController.getType().isEmpty());
  };
}