package com.group64;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws FileNotFoundException {
//        var javaVersion = SystemInfo.javaVersion();
//        var javafxVersion = SystemInfo.javafxVersion();
//
//        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
//        var scene = new Scene(new StackPane(label), 640, 480);
//        stage.setScene(scene);
//        stage.show();

        double scale = 0.5;

        Image background = new Image(new
                FileInputStream("/Users/faiqahmad/" +
                "Desktop/CS 2340/pacman/src/main/java/com" +
                "/group64/backgroundSettings.png"));

        ImageView backgroundImageView = new ImageView(background);

        backgroundImageView.setFitWidth(600);
        backgroundImageView.setFitHeight(800);

        Group root = new Group(backgroundImageView);

        Scene settingsScene = new Scene(root, Color.BLACK);



        stage.setMinHeight(800);
        stage.setMaxHeight(800);
        stage.setMinWidth(600);
        stage.setMaxWidth(600);




        String javaVersion = SystemInfo.javaVersion();
        String javafxVersion = SystemInfo.javafxVersion();
        Label label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        stage.setTitle("!PACMAN!");
        stage.setWidth(600);
        stage.setHeight(800);
        stage.setScene(settingsScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}