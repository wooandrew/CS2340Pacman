package com.group64.Map;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.File;

public class Map {

    private Points points;
    private Scene scene;
    private GraphicsContext gc;
    private Image pac;

    public Map(int pnts) {
        
        this.points = new Points(pnts);

        StackPane root = new StackPane();
        Canvas canvas = new Canvas(1200, 800);
        gc = canvas.getGraphicsContext2D();

        String stream = "assets/Final pacman.png";
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

    public void draw() {
        gc.drawImage(pac, 0, 0);
    }

    public Image getImage() {
        return pac;
    }

    public GraphicsContext getContext() {
        return gc;
    }
}
