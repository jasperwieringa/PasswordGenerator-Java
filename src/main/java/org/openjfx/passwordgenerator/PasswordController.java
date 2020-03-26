package org.openjfx.passwordgenerator;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class PasswordController {
    ObservableList<String> passwordType = FXCollections.observableArrayList("Passphrase");

    @FXML
    private ComboBox<String> passwordBox = new ComboBox<String>();

    @FXML
    private void initialize() {
        passwordBox.setValue("Password");
        passwordBox.setItems(passwordType);
    }

    // Button on click
    @FXML
    private void clickMe() throws IOException {
        System.out.println("Hey you!");
    };

    // Dropdown on action
    @FXML
    private void switchToPassphrase() throws IOException {
        App.setRoot("passphrase_generator");
    }
}
