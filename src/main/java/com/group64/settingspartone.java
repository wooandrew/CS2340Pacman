package com.group64;

public class settingspartone {
    private String ign;
    private String difficulty;

    public settingspartone() {
        ign = "";
        difficulty = "easy";
    }

    public boolean validName() {
        if (ign == "" || ign == null || ign == " " ) {
            return false;
        } else {
            return true;
        }
    }

    public String getIgn() {
        return ign;
    }

    public void setIgn(String in) {
        ign = in;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String in2) {
        difficulty = in2;
    }

}
