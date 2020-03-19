package org.openjfx.passwordgenerator;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {
    @FXML
    private void initialize() {
        
    }


    @FXML
    private void clickMe() throws IOException {
        System.out.println("Hey you!");
    };

    @FXML
    private void getChoice() throws IOException {
        System.out.println(this);
    };
}
