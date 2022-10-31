package com.group64;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class pellet {

    //initializing variables
    private Image regularPellet;
    private Image bigPellet;
    private final int pointsWorthReg;
    private final int pointsWorthBig;
    //create an arraylist of ImageView objects that holds all of the pellets on the map

    public pellet() throws FileNotFoundException {
        pointsWorthReg = 10;
        pointsWorthBig = 50;
        regularPellet = new Image(new FileInputStream("assets/regularpeller.png"));
        bigPellet = new Image(new FileInputStream("assets/bigpellet.png"));

    }

    //getter for the number of points a regular pellet is worth
    public int getPointsWorthReg() { return pointsWorthReg; }

    //getter for the number of points a big pellet is worth
    public int getPointsWorthBig() { return pointsWorthBig; }
}
