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

    private Scene scene;
    private GraphicsContext gc;
    private Image pac;
    private ArrayList<Entity> tiles;
    private D2D mapSize;

    public Map(int pnts) throws IOException {

        tiles = new ArrayList<>();

        mapSize = new D2D(37, 25);

        String mapPath = "assets/Map.txt";
        BufferedReader read = new BufferedReader(new FileReader(mapPath));
        for (int y = 0; y < 25; ++y) {
            String currentLine = read.readLine();
            for (int i = 0; i < currentLine.length(); i++) {
                int type = Integer.parseInt(String.valueOf(currentLine.charAt(i)));

                D2D size = new D2D(32, 32);
                D2D pos = new D2D(i * 32, y * 32);

                switch (type) {

                case 0:
                    tiles.add(new Entity("nil:assets/passableUpdated.png", pos, size));
                    break;
                case 1:
                    tiles.add(new Entity("wall:assets/wallUpdated.png", pos, size));
                    break;
                case 2:
                    tiles.add(new Entity("portal:assets/portalUpdated.png", pos, size));
                    break;
                default:
                    System.out.println("ERROR: Map file corrupted. Defaulting to passable tile.");
                    tiles.add(new Entity("nil:assets/passableUpdated.png", pos, size));
                    break;
                }                
            }

        }

        read.close();


        StackPane root = new StackPane();
        Canvas canvas = new Canvas(1184, 800);
        gc = canvas.getGraphicsContext2D();

        String stream = "assets/maze.png";
        pac = new Image(new File(stream).toURI().toString());
        
        ImageView image = new ImageView();
        image.setImage(pac);
        image.resize(0, 0);
        
        root.getChildren().add(canvas);
        scene = new Scene(root, 1184, 800);
    }

    public void draw(int round, int lives, int points) {

        // index = row * col + col
        for (Entity tile : tiles) {
            int x = tile.getPosition().getX();
            int y = tile.getPosition().getY();
            gc.drawImage(tile.getSprite(), x, y);
        }

        // Draw info
        gc.fillText("Lives: " + lives, 20, 30);
        gc.fillText("Round " + round, 560, 30);
        gc.fillText("Score: " + points, 1100, 30);
    }

    // Getters
    public Scene getScene() {
        return scene;
    }

    public Image getImage() {
        return pac;
    }

    public GraphicsContext getContext() {
        return gc;
    }

    public ArrayList<Entity> getTiles() {
        return tiles;
    }

    public D2D getMapSize() {
        return mapSize;
    }

    public Boolean isWall(int index) {
        return tiles.get(index).getSpriteKey().compareTo("wall") == 0;
    }
}
