package com.group64;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Character extends Entity {
    
    private int lives;          // Number of lives player has
    boolean goNorth, goSouth, goEast, goWest;
    public Character(ArrayList<String> sprites, D2D pos, D2D size) throws FileNotFoundException {
        super(sprites, pos, size);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void update(Scene scene, Entity[] walls, ArrayList<Entity> pellets) {

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
        for (Entity ent : walls) {
            boolean isCollision = collisionDetection(ent);
            if(isCollision) { // collision happens
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
        for (Entity ent : pellets) {
            boolean isCollision = collisionDetection(ent);
            if(isCollision) { // collision happens
                pellets.remove(ent);
            }
        }
    }
}
