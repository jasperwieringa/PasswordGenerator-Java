module org.openjfx.passwordgenerator {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.openjfx.passwordgenerator to javafx.fxml;
    exports org.openjfx.passwordgenerator;
}