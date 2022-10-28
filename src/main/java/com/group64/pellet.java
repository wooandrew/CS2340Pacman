package com.group64;

import javafx.scene.image.Image;

public class pellet {

    //initializing variables
    private Image regularPellet;
    private Image bigPellet;
    private int pointsWorthReg;
    private int pointsWorthBig;

    public pellet() {
        pointsWorthReg = 10;
        pointsWorthBig = 50;
    }

    //getter for the number of points a regular pellet is worth
    public int getPointsWorthReg() {return pointsWorthReg;}

    //getter for the number of points a big pellet is worth
    public int getPointsWorthBig() {return pointsWorthBig;}
}
