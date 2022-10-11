package com.group64;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.group64.GameManager.State;

/**
 * JavaFX App
 */
public class App extends Application {

    String title = "Group64 Pacman";
    GameManager gm = new GameManager(State.HOME);

    @Override
    public void start(Stage stage) {

        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 1200, 800);
        
        stage.setTitle(title);
        stage.setScene(scene);
        

        AnimationTimer loop = new AnimationTimer() {

            @Override
            public void handle(long now) {
                System.out.println("Idea!");
                gm.setState(State.GAMEOVER);
            }
        };
        loop.start();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}