package com.group64;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

public class Character {
    
    private int lives;                      // Number of lives player has
    private Dimension2D position;           // Position in x,y coords
    private Dimension2D size;               // Size of character, in pixels
    private Image[] sprites;                // Array of character sprites

    public Character(int lives, Dimension2D pos, Dimension2D size) {
        this.lives = lives;
        this.position = pos;
        this.size = size;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void update() {

        // TODO: any updates the player has should occur here, 
        // including movemet and collision detection.
        // Should be polled every loop iteration
    }
}
