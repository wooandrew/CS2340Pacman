package com.group64;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class settingspartone {
    private String ign;
    private String difficulty;

    public settingspartone() {
        ign = "";
        difficulty = "easy";
    }

    //runs the actual game
    public void settingscene(Stage stage) {
        VBox setttingNode = new VBox();
        Scene settingsScene =  new Scene(setttingNode);



        stage.setScene(settingsScene);
        stage.show();
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
