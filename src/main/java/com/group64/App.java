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

import java.io.IOException;
import java.util.ArrayList;

import com.group64.GameManager.State;
import com.group64.Map.Map;

/**
 * JavaFX App
 */
public class App extends Application {

    private String title = "Group64 Pacman";
    private GameManager gm = new GameManager(State.HOME);
    private Settings st;
    private Map mp;
    private Character player;

    @Override
    public void start(Stage stage) throws IOException {

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
        vbox.getChildren().add(exit);
        vbox.setSpacing(30);
        vbox.setAlignment(Pos.CENTER);

        var scene = new Scene(vbox, 1200, 800);
        scene.setFill(Color.BLACK);

        start.setOnAction(event -> {
            stage.setScene(st.getScene());
            gm.setState(State.INGAME);
        });
        exit.setOnAction(event -> Platform.exit());

        stage.setScene(scene);
        stage.setTitle(title);

        mp = new Map(30);

        // Initialize Player
        D2D position = new D2D(500, 400);
        D2D size = new D2D(16, 16);
        ArrayList<String> imgKey = new ArrayList<String>();
        imgKey.add("yellow:assets/pacmanYellow.png");
        imgKey.add("gray:assets/pacmanGray.png");
        imgKey.add("magenta:assets/pacmanMagenta.png");
        
        try {
            player = new Character(imgKey, position, size);
        } catch (Exception e) {
            System.out.println("FATAL ERROR: FAILED TO INITIALIZE PLAYER: " + e.getMessage());
            Platform.exit();
        }

        // Creating the Ghosts


        ArrayList<Pellet> pellets = new ArrayList<>();

        String bigPelletKey = "big:assets/bigpellet.png";
        String regPelletKey = "regular:assets/regularpellet.png";

        pellets.add(new Pellet(bigPelletKey, new D2D(72, 72), new D2D(12, 12)));
        pellets.add(new Pellet(bigPelletKey, new D2D(72, 552), new D2D(12, 12)));
        pellets.add(new Pellet(bigPelletKey, new D2D(1105, 72), new D2D(12, 12)));
        pellets.add(new Pellet(bigPelletKey, new D2D(1105, 552), new D2D(12, 12)));
        pellets.add(new Pellet(regPelletKey, new D2D(118, 72), new D2D(8, 8)));
        pellets.add(new Pellet(regPelletKey, new D2D(165, 72), new D2D(8, 8)));
        pellets.add(new Pellet(regPelletKey, new D2D(212, 72), new D2D(8, 8)));
        pellets.add(new Pellet(regPelletKey, new D2D(259, 72), new D2D(8, 8)));
        pellets.add(new Pellet(regPelletKey, new D2D(306, 72), new D2D(8, 8)));
        pellets.add(new Pellet(regPelletKey, new D2D(353, 72), new D2D(8, 8)));

//        ArrayList<Ghost> ghosts = new ArrayList<>();
//
//        ghosts.add(new Ghost());
//        ghosts.add(new Ghost());
//        ghosts.add(new Ghost());
//        ghosts.add(new Ghost());


        // Initialize settings
        st = new Settings(stage, mp.getScene(), player);

        AnimationTimer loop = new AnimationTimer() {

            @Override
            public void handle(long now) {

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

                    // Clear screen
                    mp.getContext().clearRect(0, 0, 1200, 800);
                    
                    // Update entities
                    player.update(stage.getScene(), mp.getWalls(), pellets);

                    // Draw
                    mp.draw(gm.getCurrentRound(), player.getLives(), player.getScore());
                    player.draw(mp.getContext());
                    for (Pellet pellet : pellets) {
                        pellet.draw(mp.getContext());
                    }


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