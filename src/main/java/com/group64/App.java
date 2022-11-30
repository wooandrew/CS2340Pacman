package com.group64;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    // In Game variables
    private ArrayList<Pellet> pellets;
    private ArrayList<Ghost> ghosts;

    public void setUp() throws FileNotFoundException {

        pellets = new ArrayList<>();
        String bigPelletKey = "big:assets/bigpellet.png";
        String regPelletKey = "regular:assets/regularpellet.png";
        String lifePelletKey = "addLife:assets/lifePellet.png";

        pellets.add(new Pellet(lifePelletKey, new D2D(32 + 10, 32 + 10), new D2D(12, 12)));
        pellets.add(new Pellet(lifePelletKey, new D2D(1120 + 10, 736 + 10), new D2D(12, 12)));
        pellets.add(new Pellet(bigPelletKey, new D2D(1120 + 10, 32 + 10), new D2D(12, 12)));
        pellets.add(new Pellet(bigPelletKey, new D2D(32 + 10, 736 + 10), new D2D(12, 12)));
        for (int pel = 0; pel < mp.getTiles().size(); pel++) {
            if (mp.getTiles().get(pel).getSpriteKey().equals("nil") && pel != 38 && pel != 72
                    && pel != 852 && pel != 886 && pel != 460 && pel != 461 && pel != 462
                    && pel != 463 && pel != 464 && pel != 684 && pel != 425) {
                pellets.add(new Pellet(regPelletKey,
                        new D2D(mp.getTiles().get(pel).getPosition().getX() + 10,
                                mp.getTiles().get(pel).getPosition().getY() + 10), new D2D(8, 8)));
            }
        }

        ghosts = new ArrayList<>();
        ghosts.add(new Ghost("Red:assets/redGhost.png", new D2D(544, 384), new D2D(32, 32)));
        ghosts.add(new Ghost("Yellow:assets/yellowGhost.png", new D2D(576, 384), new D2D(32, 32)));
        ghosts.add(new Ghost("Tan:assets/tanGhost.png", new D2D(608, 384), new D2D(32, 32)));
        ghosts.add(new Ghost("Green:assets/greenGhost.png", new D2D(640, 384), new D2D(32, 32)));
    }

    void setUpVBox1(VBox vbox, Font f1, Button start) throws FileNotFoundException {

        start.setPrefSize(95, 25);
        start.setFont(f1);
        start.setStyle("-fx-background-color: #778899");
        start.setTextFill(Color.YELLOW);

        Button exit = new Button("Exit");
        exit.setPrefSize(95, 25);
        exit.setFont(f1);
        exit.setStyle("-fx-background-color: #778899");
        exit.setTextFill(Color.YELLOW);
        exit.setOnAction(event -> Platform.exit());

        vbox.getStyleClass().add("color-palette");
        String sImg = "assets/PManStart.jpeg";
        vbox.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream(sImg)),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 
            0, true, Side.BOTTOM, 0, true), new BackgroundSize(BackgroundSize.AUTO, 
            BackgroundSize.AUTO, true, true, false, true))));
        vbox.getChildren().addAll(start, exit);
        vbox.setSpacing(30);
        vbox.setAlignment(Pos.CENTER);
    }

    void setUpVBox2(VBox vbox, Font f1, Font f2, Stage stage, Scene scene) {

        Button exit1 = new Button("Exit");
        exit1.setPrefSize(95, 25);
        exit1.setFont(f1);
        exit1.setStyle("-fx-background-color: #778899");
        exit1.setTextFill(Color.YELLOW);
        exit1.setOnAction(event -> Platform.exit());
       
        vbox.getStyleClass().add("color-palette");

        Button restart = new Button("Restart");
        restart.setPrefSize(95, 25);
        restart.setFont(f1);
        restart.setStyle("-fx-background-color: #778899");
        restart.setTextFill(Color.YELLOW);
        restart.setOnAction(e -> {
            stage.setScene(scene);
        });

        String over = "assets/GameOver.png";
        vbox.getChildren().addAll(restart, exit1);
        vbox.setSpacing(15);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        try {
            vbox.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream(
                over)), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, 
                new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true), 
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false,
                true))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void setUpVBox3(VBox vbox, Font f1, Font f2, Stage stage, Scene scene) {

        Button exit = new Button("Exit");
        exit.setPrefSize(95, 25);
        exit.setFont(f1);
        exit.setStyle("-fx-background-color: #778899");
        exit.setTextFill(Color.BLACK);
        exit.setOnAction(event -> Platform.exit());

        vbox.getStyleClass().add("color-palette");
        
        Button playAgain = new Button("Play Again");
        playAgain.setPrefSize(150, 25);
        playAgain.setFont(f1);
        playAgain.setStyle("-fx-background-color: #778899");
        playAgain.setTextFill(Color.BLACK);

        playAgain.setOnAction(e -> {
            stage.setScene(scene);
        });

        String over = "assets/FINALFORTNITE.png";

        vbox.getChildren().addAll(playAgain, exit);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        try {
            vbox.setBackground(new Background(new BackgroundImage(new Image(
                new FileInputStream(over)), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false,
                true)))
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {

        Font f1 = Font.font("Times New Roman", FontWeight.BOLD, 20);
        Font f2 = Font.font("Times New Roman", FontWeight.BOLD, 45);

        var vbox = new VBox();
        Button start = new Button("Start");
        setUpVBox1(vbox, f1, start);
        var scene = new Scene(vbox, 1184, 800);
        scene.setFill(Color.BLACK);

        var vbox2 = new VBox();
        Label score = new Label("Hello World");
        score.setFont(f2);
        Label lives = new Label("Hello World");
        lives.setFont(f2);
        Label ghostsEaten = new Label("Ghosts eaten: ");
        ghostsEaten.setFont(f2);
        vbox2.getChildren().addAll(score, lives, ghostsEaten);
        setUpVBox2(vbox2, f1, f2, stage, scene);
        var scene2 = new Scene(vbox2, 1184, 800);

        var vbox3 = new VBox();
        Label score1 = new Label("Final Score: ");
        score1.setFont(f2);
        Label lives1 = new Label("Lives Remaining: ");
        lives1.setFont(f2);
        Label ghostsEaten1 = new Label("Ghosts eaten: ");
        ghostsEaten1.setFont(f2);
        vbox3.getChildren().addAll(score1, lives1, ghostsEaten1);
        setUpVBox3(vbox3, f1, f2, stage, scene);
        var scene3 = new Scene(vbox3, 1184, 800);

        stage.setScene(scene);
        stage.setTitle(title);

        mp = new Map(30);

        // Initialize Player
        D2D size = new D2D(32, 32);
        ArrayList<String> imgKey = new ArrayList<String>();
        imgKey.add("yellow:assets/pacmanYellow.png");
        imgKey.add("gray:assets/pacmanGray.png");
        imgKey.add("magenta:assets/pacmanMagenta.png");

        setUp();

        try {
            player = new Character(imgKey, size);
        } catch (Exception e) {
            System.out.println("FATAL ERROR: FAILED TO INITIALIZE PLAYER: " + e.getMessage());
            Platform.exit();
        }

        // Initialize settings
        st = new Settings(stage, gm, mp.getScene(), player);

        AnimationTimer loop = new AnimationTimer() {

            @Override
            public void handle(long now) {

                if (gm.getState() == State.INGAME) {

                    if (player.getLives() <= 0) {
                        score.setText("Final Score: " + player.getScore());
                        lives.setText("Lives Remaining: " + player.getLives());
                        ghostsEaten.setText("Ghosts Eaten: " + player.getGhostsEaten());
                        stage.setScene(scene2);
                        gm.setState(State.GAMEOVER);
                    }
                    if (pellets.size() == 0) {
                        score1.setText("Final Score: " + player.getScore());
                        lives1.setText("Lives Remaining: " + player.getLives());
                        ghostsEaten1.setText("Ghosts Eaten: " + player.getGhostsEaten());
                        stage.setScene(scene3);
                        gm.setState(State.GAMEOVER);
                    }

                    // Clear screen
                    mp.getContext().clearRect(0, 0, 1184, 800);

                    // Update entities
                    player.update(stage.getScene(), mp, pellets, ghosts);

                    // Draw
                    mp.draw(gm.getCurrentRound(), player.getLives(), player.getScore());
                    for (Pellet pellet : pellets) {
                        pellet.draw(mp.getContext());
                    }
                    player.draw(mp.getContext());
                    for (Ghost ghost : ghosts) {
                        ghost.update(mp, gm.getRandom());
                        ghost.draw(mp.getContext());
                    }
                }

                if (gm.getState() == State.GAMEOVER) {

                    try {
                        setUp();
                        player.reset();
                    } catch (Exception e) {
                        System.out.println("FATAL ERROR: " + e.getMessage());
                    }

                    this.stop();
                }
            }
        };

        start.setOnAction(event -> {
            stage.setScene(st.getScene());
            gm.setState(State.SETTINGS);
            loop.start();
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
