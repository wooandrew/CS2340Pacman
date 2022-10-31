package com.group64.Map.Tiles;

import java.awt.image.BufferedImage;

public class Tile {

    protected boolean passable;
    protected BufferedImage image;


    public boolean getPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }
}
