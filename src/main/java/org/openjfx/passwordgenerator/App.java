// Naam: Jasper Wieringa
// Leerlijn: Object Georienteerd programmeren
// Datum: 27-04-2020

package org.openjfx.passwordgenerator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
  private static Scene password_scene;

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage window) throws IOException {
    // Geef de titel van de window
    window.setTitle("Password Generator");

    // Maak een scene aan
    password_scene = new Scene(loadFXML("password_generator"));

    // Set de scene en laat deze zien
    window.setScene(password_scene);
    window.show();
  }

  static void setRoot(String fxml) throws IOException {
    password_scene.setRoot(loadFXML(fxml));
  }

  // Zoek een fxml bestand op basis van een gegeven String
  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }
}