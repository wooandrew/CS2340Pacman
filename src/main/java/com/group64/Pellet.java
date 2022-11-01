package com.group64;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
        }
    }

    //getter for the number of points a regular pellet is worth
    public int getPointsWorth() {
        return pointsWorth;
    }

}
