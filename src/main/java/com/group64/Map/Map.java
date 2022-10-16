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
    Scene scene;

    public Map(Points points) {
        this.points = points;

    }

    public Scene getScene() {

        StackPane root = new StackPane();
        Canvas canvas = new Canvas( 800, 500 );
        GraphicsContext gc = canvas.getGraphicsContext2D();
        String stream = "\\Georgia tech\\CS 2340\\S1\\pacman\\src\\main\\java\\com\\group64\\Final pacman.png";
        Image pac = new Image(new File(stream).toURI().toString());
        ImageView image = new ImageView();
        image.setImage(pac);
        image.resize(0, 0);
        gc.drawImage( pac, 0, 0 );
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 800, 500);

        return scene;
    }
}
