package org.openjfx.passwordgenerator;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class PrimaryController {
    ObservableList<String> passwordType = FXCollections.observableArrayList("Password", "Passphrase");

    @FXML
    private ChoiceBox<String> passwordBox = new ChoiceBox<String>();

    @FXML
    private void initialize() {
        passwordBox.setValue("Password");
        passwordBox.setItems(passwordType);
    }

    @FXML
    private void clickMe() throws IOException {
        System.out.println("Hey you!");
    };
}
