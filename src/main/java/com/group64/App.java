package com.group64;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.group64.GameManager.State;

/**
 * JavaFX App
 */
public class App extends Application {

    String title = "Group64 Pacman";
    GameManager gm = new GameManager(State.HOME);

    void none() {

    };

    @Override
    public void start(Stage stage) {

        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        Label pacman = new Label("PACMAN");
        Font f1 = Font.font("Times New Roman", FontWeight.BOLD, 100);
        Font f2 = Font.font("Times New Roman", FontWeight.BOLD, 20);
        pacman.setFont(f1);
        pacman.setTextFill(Color.RED);
        Button start = new Button("Start");
        start.setPrefSize(95,25);
        start.setFont(f2);
        start.setTextFill(Color.BLACK);
        Button settings = new Button("Settings");
        settings.setPrefSize(95,25);
        settings.setFont(f2);
        settings.setTextFill(Color.BLACK);
        Button exit = new Button("Exit");
        exit.setPrefSize(95,25);
        exit.setFont(f2);
        exit.setTextFill(Color.BLACK);
        var vbox = new VBox();
        vbox.getStyleClass().add("color-palette");
        vbox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        vbox.getChildren().add(pacman);
        vbox.getChildren().add(start);
        vbox.getChildren().add(settings);
        vbox.getChildren().add(exit);
        vbox.setSpacing(30);
        vbox.setAlignment(Pos.CENTER);

        Stage newWindow = new Stage();
        newWindow.setTitle("Instructions");

        var scene = new Scene(vbox, 1200, 800);
        scene.setFill(Color.BLACK);



        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");

        var scene2 = new Scene(new StackPane(label), 1200, 800);


        start.setOnAction((EventHandler) event -> stage.setScene(scene2));
        //settings.setOnAction((EventHandler) event -> "FAIQ and SNEH enter your code here");
        exit.setOnAction((EventHandler) event -> Platform.exit());




        stage.setTitle(title);
        
        AnimationTimer loop = new AnimationTimer() {

            @Override
            public void handle(long now) {
                
                gm.setState(State.GAMEOVER);

                switch (gm.getState()) {

                    case HOME:
                        



                    // Set next scene
                    // stage.setScene(class.scene)
                    break;
                    case SETTINGS:

                    // Set next scene
                    // stage.setScene(class.scene)
                    break;
                    case INGAME:

                    // Set next scene
                    // stage.setScene(class.scene)
                    break;
                    case GAMEOVER:

                    // Set next scene
                    // stage.setScene(class.scene)
                    break;
                }
            }
        };
        loop.start();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}