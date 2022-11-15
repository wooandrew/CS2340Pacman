package com.group64;

import java.util.Random;

public class GameManager {
    
    private State state;
    private int currentRound;
    private int pointsAccumulated;
    private Random random;          // Random number generator

    public enum State {
        HOME, SETTINGS, INGAME, GAMEOVER
    }

    public GameManager(State state) {
        this.state = state;
        currentRound = 1;
        pointsAccumulated = 0;
        random = new Random(System.currentTimeMillis());
    }

    // Functions
    public void nextRound() {
        currentRound += 1;
    }

    public void addPoint() {
        pointsAccumulated += 1;
    }

    public void addPoints(int amount) {
        pointsAccumulated += amount;
    }

    // Setters
    public void setState(State state) {
        this.state = state;
    }

    public void setCurrentRound(int round) {
        currentRound = round;
    }

    public void setPointsAccumulated(int points) {
        pointsAccumulated = points;
    }

    // Getters
    public State getState() {
        return state;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getPointsAccumulated() {
        return pointsAccumulated;
    }

    public Random getRandom() {
        return random;
    }
}
