package com.group64.Map;

import com.group64.Entity;
import com.group64.D2D;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.*;
import java.util.ArrayList;

public class Map {

    private Points points; // points that will be available on map
    private Scene scene;
    private GraphicsContext gc;
    private Image pac;
    private ArrayList<Entity> walls;

    public Map(int pnts) throws IOException {

        walls = new ArrayList<>();

        String mapPath = "assets/Map.txt";
        BufferedReader read = new BufferedReader(new FileReader(mapPath));
        for (int y = 0; y < 25; ++y) {
            String currentLine = read.readLine();
            for (int i = 0; i < currentLine.length(); i++) {
                int type = Integer.parseInt(String.valueOf(currentLine.charAt(i)));

                D2D size = new D2D(32, 32);
                D2D pos = new D2D(i * 32, y * 32);

                String path;

                switch (type) {

                case 0:
                    path = "nil:assets/passableUpdated.png";
                    break;
                case 1:
                    path = "wall:assets/wallUpdated.png";
                    break;
                case 2:
                    path = "portal:assets/portalUpdated.png";
                    break;
                default:
                    path = "";
                    break;
                }

                Entity wall = new Entity(path, pos, size);
                walls.add(wall);
            }

        }

        read.close();
        
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

    public void draw(int round, int lives, int points) {

        // index = row * col + col
        for (Entity wall : walls) {
            int x = wall.getPosition().getX();
            int y = wall.getPosition().getY();
            gc.drawImage(wall.getSprite(), x, y);
        }

        // Draw info
        gc.fillText("Lives: " + lives, 20, 30);
        gc.fillText("Round " + round, 560, 30);
        gc.fillText("Score: " + points, 1100, 30);
    }

    // Getters
    public Points getPoints() {
        return points;
    }

    public Scene getScene() {
        return scene;
    }

    public Image getImage() {
        return pac;
    }

    public GraphicsContext getContext() {
        return gc;
    }

    public ArrayList<Entity> getWalls() {
        return walls;
    }
}
