package com.group64;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var jVer = SystemInfo.javaVersion();
        var jfxVer = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + jfxVer + ", running on Java " + jVer + ".~");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}