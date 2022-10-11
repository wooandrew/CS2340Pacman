package com.group64;

public class GameManager {
    
    public enum State {
        HOME, SETTINGS, INGAME, GAMEOVER
    }

    private State state;

    public GameManager(State _state) {
        state = _state;

    }

    // Setters
    public void setState(State _state) {
        state = _state;
    }

    // Getters
    public State getState() {
        return state;
    }
}
