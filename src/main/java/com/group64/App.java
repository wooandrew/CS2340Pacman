package com.group64;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        URL url = new File("src/main/java/com/group64/settings.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
//        Parent root = FXMLLoader.load(getClass().getResource("/settings.fxml"));
        Scene settingsScene = new Scene(root);
        stage.setScene(settingsScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}