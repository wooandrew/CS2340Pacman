package com.group64;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Character extends Entity {
    
    private int lives;          // Number of lives player has
    private int score;

    private boolean goNorth;
    private boolean goSouth;
    private boolean goEast;
    private boolean goWest;

    public Character(ArrayList<String> sprites, D2D pos, D2D size) throws FileNotFoundException {
        super(sprites, pos, size);

        score = 0;
        lives = 3;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void update(Scene scene, ArrayList<Entity> walls, ArrayList<Pellet> pellets) {

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        goNorth = true;
                        break;
                    case DOWN:
                        goSouth = true;
                        break;
                    case LEFT:
                        goWest  = true;
                        break;
                    case RIGHT:
                        goEast  = true;
                        break;
                    default:

                        break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        goNorth = false;
                        break;
                    case DOWN:
                        goSouth = false;
                        break;
                    case LEFT:
                        goWest  = false;
                        break;
                    case RIGHT:
                        goEast  = false;
                        break;

                    default:
                        break;
                }
            }
        });

        if (goNorth) {
            position.setY(position.getY() - 1);
        }
        if (goSouth) {
            position.setY(position.getY() + 1);
        }
        if (goEast) {
            position.setX(position.getX() + 1);
        }
        if (goWest) {
            position.setX(position.getX() - 1);
        }

        //collision for wall
        for (Entity wall : walls) {

            boolean isCollision = collisionDetection(wall);

            if (isCollision && wall.getSpriteKey().compareTo("wall") == 0) { // collision happens
                
                if (goNorth) {
                    position.setY(position.getY() + 1);
                }
                if (goSouth) {
                    position.setY(position.getY() - 1);
                }
                if (goEast) {
                    position.setX(position.getX() - 1);
                }
                if (goWest) {
                    position.setX(position.getX() + 1);
                }
            }
        }
        //collision check for pellets
        for (int x = 0; x < pellets.size(); ++x) {

            if (collisionDetection(pellets.get(x))) { // collision happens
                score += pellets.get(x).getPointsWorth();
                pellets.remove(x);
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(getSprite(), position.getX(), position.getY());

        // Hitbox debugging
        //gc.fillRect(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }
}
