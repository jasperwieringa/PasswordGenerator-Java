package org.openjfx.passwordgenerator;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class PassphraseController extends Controller {
    ObservableList<String> passwordType = FXCollections.observableArrayList("Password");

    @FXML
    private ComboBox<String> passwordBox = new ComboBox<String>();

    @FXML @Override 
    protected void initialize() {
      passwordBox.setValue("Passphrase");
      passwordBox.setItems(passwordType);
    }

    @FXML @Override
    protected void generatePassword() throws IOException {
        // Do something here
    }

    @FXML @Override
    protected void copyPassword() throws IOException {
        // Do something here
    }

    @FXML @Override
    protected void switchTo() throws IOException {
      App.setRoot("password_generator");
    }
}
