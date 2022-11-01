package com.group64;

import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Character extends Entity {
    
    private int lives;          // Number of lives player has

    public Character(ArrayList<String> sprites, D2D pos, D2D size) throws FileNotFoundException {
        super(sprites, pos, size);
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
