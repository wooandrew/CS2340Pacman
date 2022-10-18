package com.group64;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Settings {

    private String ign;
    private String difficulty;
    private ImageView selectedCharacter;

    private GridPane pane;
    private TextField name;
    private Button submit;
    private Label check;
    private Label check2;
    private ComboBox<String> comboBox;
    private Scene settings;



    private Button goButton;

    private ComboBox<String> characterSelect;
    private TextField displayFinalName;
    private Image yellowPacman = new Image(new FileInputStream("./src/main/java/com/group64/Images/yellowPacman.png"));
    private Image grayPacman = new Image(new FileInputStream("./src/main/java/com/group64/Images/grayPacman.png"));
    private Image magentaPacman = new Image(new FileInputStream("./src/main/java/com/group64/Images/magentaPacman.png"));

    public Settings(Stage game) throws FileNotFoundException {
        displayFinalName = new TextField();
        ign = null;
        difficulty = "easy";
        pane = new GridPane();
        name = new TextField();
        submit = new Button("Submit");
        check = new Label();
        comboBox = new ComboBox();
        check2 = new Label();
        settings = new Scene(pane);
        selectedCharacter = new ImageView(yellowPacman);
        goButton = new Button("Go Forward");
        characterSelect = new ComboBox<>();

        game.setTitle("pacman settings");
        game.setWidth(600);
        game.setHeight(800);

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);
        pane.setHgap(5);

        name.setPromptText("Enter name.");
        pane.add(name, 70, 50, 1, 1);

        pane.add(check, 70, 51, 1, 1);

        pane.add(selectedCharacter, 72, 50, 2, 1);

        pane.add(displayFinalName, 80, 80, 1, 1);
        //link to the combobox

        pane.add(goButton,60, 40, 2, 11);
        goButton.setVisible(false);
        goButton.setOnAction(e -> {
            //get starting stage?
            //startingstage.setScene(gameScene);
        });


        pane.add(characterSelect,40, 30, 1, 1);
        //add funtionality
        characterSelect.getItems().add("Yellow");
        characterSelect.getItems().add("Gray");
        characterSelect.getItems().add("Magenta");

        characterSelect.setOnAction(e -> {
            if (characterSelect.getValue() == "Yellow") {
                selectedCharacter = new ImageView(yellowPacman);
            } else if (characterSelect.getValue() == "Magenta") {
                selectedCharacter = new ImageView(magentaPacman);
            } else if (characterSelect.getValue() == "Gray") {
                selectedCharacter = new ImageView(grayPacman);
            }
        });

        pane.add(submit, 72, 50, 2, 1);
        submit.setOnAction(e -> {
            if (badName(name.getText())) {
                check.setText("not a valid name");
                goButton.setVisible(false);
            } else {
                check.setText("Thanks");
                setIgn(name.getText());
                goButton.setVisible(true);
            }
        });

        pane.add(check2, 70, 56, 1, 1);
        check2.setText("Difficulty Selected: " + difficulty);

        comboBox.setPromptText("Difficulty");
        comboBox.getItems().add("easy");
        comboBox.getItems().add("medium");
        comboBox.getItems().add("hard");
        pane.add(comboBox, 70, 55, 1, 1);

        comboBox.setOnAction(e -> {
            //System.out.println(comboBox.getValue());
            setDifficulty((String) comboBox.getValue());
            check2.setText("Difficulty Selected: " + difficulty);
        });

        getScene();
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

    public Scene getScene() {
        return settings;
    }
}
