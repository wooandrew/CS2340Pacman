package com.group64;

public class D2D {
    
    private int x;
    private int y;

    public D2D() {
        x = 0;
        y = 0;
    }

    public D2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return x;
    }

    public int getHeight() {
        return y;
    }
}
