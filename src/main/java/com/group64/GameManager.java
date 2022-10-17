package com.group64;

public class GameManager {
    
    public enum State {
        HOME, SETTINGS, INGAME, GAMEOVER
    }

    private State state;

    public GameManager(State state) {
        this.state = state;

    }

    // Setters
    public void setState(State state) {
        this.state = state;
    }

    // Getters
    public State getState() {
        return state;
    }
}
