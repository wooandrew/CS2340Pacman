package com.group64.Map;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.File;

public class Map {

    private Points points; // points that will be available on map
    private Scene scene;
    private GraphicsContext gc;
    private Image pac;

    public Map(int pnts) {
        
        this.points = new Points(pnts);

        StackPane root = new StackPane();
        Canvas canvas = new Canvas(1200, 800);
        gc = canvas.getGraphicsContext2D();

        String stream = "assets/maze.png";
        pac = new Image(new File(stream).toURI().toString());
        
        ImageView image = new ImageView();
        image.setImage(pac);
        image.resize(0, 0);
        
        root.getChildren().add(canvas);
        scene = new Scene(root, 1200, 800);
    }

    // Getters
    public Points getPoints() {
        return points;
    }

    public Scene getScene() {
        return scene;
    }

    public void draw(int round, int lives, int points) {
        gc.drawImage(pac, 380, 250);
        gc.fillText("Lives: " + lives, 20, 30);
        gc.fillText("Round " + round, 560, 30);
        gc.fillText("Score: " + points, 1100, 30);
    }

    public Image getImage() {
        return pac;
    }

    public GraphicsContext getContext() {
        return gc;
    }
}