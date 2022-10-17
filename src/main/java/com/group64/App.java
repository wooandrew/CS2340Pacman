package com.group64;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import com.group64.GameManager.State;

/**
 * JavaFX App
 */
public class App extends Application {

    private String title = "Group64 Pacman";
    private GameManager gm = new GameManager(State.HOME);

    void none() {

    };

    @Override
    public void start(Stage stage) {

        // var jVer = SystemInfo.javaVersion();
        // var jfxVer = SystemInfo.javafxVersion();
        // var label = new Label("Hello, JavaFX " + jVer + ", running on Java " + jfxVer + ".");

        Label pacman = new Label("PACMAN");
        Font f1 = Font.font("Times New Roman", FontWeight.BOLD, 100);
        Font f2 = Font.font("Times New Roman", FontWeight.BOLD, 20);
        pacman.setFont(f1);
        pacman.setTextFill(Color.RED);
        
        Button start = new Button("Start");
        start.setPrefSize(95, 25);
        start.setFont(f2);
        start.setTextFill(Color.BLACK);

        Button settings = new Button("Settings");
        settings.setPrefSize(95, 25);
        settings.setFont(f2);
        settings.setTextFill(Color.BLACK);

        Button exit = new Button("Exit");
        exit.setPrefSize(95, 25);
        exit.setFont(f2);
        exit.setTextFill(Color.BLACK);

        var vbox = new VBox();
        vbox.getStyleClass().add("color-palette");

        BackgroundFill bgFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);

        vbox.setBackground(new Background(bgFill));
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



        


        start.setOnAction(event -> none());
        //settings.setOnAction((EventHandler) event -> "FAIQ and SNEH enter your code here");
        exit.setOnAction(event -> Platform.exit());



        stage.setScene(scene);
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
                    
                    default:

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