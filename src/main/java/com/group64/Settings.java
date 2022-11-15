package com.group64;

import javafx.stage.Stage;

import com.group64.GameManager.State;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Settings {

    private Scene settings;

    private String ign;
    private String difficulty;
    private ImageView selectedCharacter;

    private GridPane pane;
    private TextField name;
    private Button submit;
    private Label check;
    private Label check2;
    private ComboBox<String> comboBox;

    private Button goButton;

    private ComboBox<String> characterSelect;

    private Label check3;

    public Settings(Stage stage, GameManager gm, Scene nxt, Character player) {

        ign = null;
        difficulty = "easy";
        pane = new GridPane();
        name = new TextField();
        submit = new Button("Submit");
        check = new Label();
        comboBox = new ComboBox<String>();
        check2 = new Label();
        settings = new Scene(pane, 1200, 800);
        selectedCharacter = new ImageView();
        goButton = new Button("Go Forward");
        characterSelect = new ComboBox<>();
        check3 = new Label();

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);
        pane.setHgap(5);

        name.setPromptText("Enter name.");

        Label setName = new Label("Set Name");

        pane.add(setName, 70, 49, 1, 1);
        pane.add(name, 70, 50, 1, 1);
        pane.add(check, 70, 51, 1, 1);
        pane.add(selectedCharacter, 72,  80, 2, 1);
        
        selectedCharacter.setFitWidth(144);
        selectedCharacter.setFitHeight(144);
        selectedCharacter.setImage(player.getSprite("yellow"));

        pane.add(check3, 70, 62, 1, 1);
        check3.setText("Character Selected: Yellow");
        //link to the combobox

        pane.add(goButton, 70, 64, 2, 1);
        goButton.setVisible(false);
        goButton.setOnAction(e -> {
            stage.setScene(nxt);
            gm.setState(State.INGAME);
        });

        pane.add(characterSelect, 70, 60, 2, 1);
        //add funtionality
        characterSelect.getItems().add("Yellow");
        characterSelect.getItems().add("Gray");
        characterSelect.getItems().add("Magenta");
        characterSelect.setPromptText("Select Character");

        characterSelect.setOnAction(e -> {
            player.setSpriteKey(characterSelect.getValue().toLowerCase());
            selectedCharacter.setImage(player.getSprite());
            check3.setText("Character Selected: " + characterSelect.getValue());
        });

        pane.add(submit, 72, 50, 2, 1);
        submit.setOnAction(e -> {
            if (badName(name.getText())) {
                check.setText("Not a valid name!");
                goButton.setVisible(false);
            } else {
                check.setText("Thanks!");
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
    }

    public boolean badName(String str) {
        return (str.isEmpty() || str.trim().length() == 0 || str == null);
    }

    public String getIgn() {
        return ign;
    }

    public void setIgn(String ign) {
        this.ign = ign;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Scene getScene() {
        return settings;
    }
}
