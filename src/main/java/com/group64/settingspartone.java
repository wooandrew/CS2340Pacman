package com.group64;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class settingspartone extends Application {
    private String ign;
    private String difficulty;

    public settingspartone() {
        ign = null;
        difficulty = "easy";
    }

    public boolean badName(String str) {
        if (str.isEmpty() || str.trim().length() == 0 || str == null) {
            return true;
        } else {
            return false;
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

    @Override
    public void start(Stage game) {
        game.setTitle("pacman settings");
        game.setWidth(600);
        game.setHeight(800);

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);
        pane.setHgap(5);

        TextField name = new TextField();
        name.setPromptText("Enter name.");
        pane.add(name, 70, 50, 1, 1);

        Button submit = new Button("Submit");
        pane.add(submit, 72, 50, 2, 1);

        Label check = new Label();
        pane.add(check, 70, 51, 1, 1);

        submit.setOnAction(e -> {
            if (badName(name.getText())) {
                check.setText("not a valid name");
            } else {
                check.setText("Thanks");
                setIgn(name.getText());
            }
        });

        ComboBox comboBox = new ComboBox();
        comboBox.setPromptText("Difficulty");
        comboBox.getItems().add("easy");
        comboBox.getItems().add("medium");
        comboBox.getItems().add("hard");
        pane.add(comboBox, 70, 55, 1, 1);

        comboBox.setOnAction(e -> {
            //System.out.println(comboBox.getValue());
            difficulty = (String) comboBox.getValue();
            System.out.println(difficulty);
        });


        Scene settings = new Scene(pane);
        game.setScene(settings);
        game.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
