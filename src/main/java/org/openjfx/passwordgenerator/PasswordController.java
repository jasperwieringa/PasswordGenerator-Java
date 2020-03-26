package org.openjfx.passwordgenerator;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class PasswordController extends Controller {
    ObservableList<String> passwordType = FXCollections.observableArrayList("Passphrase");

    @FXML
    private ComboBox<String> passwordBox = new ComboBox<String>();

    @FXML @Override
    protected void initialize() {
      passwordBox.setValue("Password");
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
      App.setRoot("passphrase_generator");
    }

}
