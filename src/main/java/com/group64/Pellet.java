package com.group64;

import java.io.FileNotFoundException;

public class Pellet extends Entity {

    //initializing variables
    private int pointsWorth;

    public Pellet(String imgKey, D2D pos, D2D size) throws FileNotFoundException {
        super(imgKey, pos, size);
        pointsWorth = 0;
        setPointsWorth(getSpriteKey());
    }

    //changes the points that the pellet is worth depending on the type of pellet
    public void setPointsWorth(String input) {
        if (input.equals("regular")) {
            pointsWorth = 10;
        } else if (input.equals("big")) {
            pointsWorth = 50;
        } else if (input.equals("addLife")) {
            pointsWorth = 50;
        }
    }

    //getter for the number of points a regular pellet is worth
    public int getPointsWorth() {
        return pointsWorth;
    }

}
