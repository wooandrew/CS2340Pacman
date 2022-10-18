package com.group64;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsControllerClass implements Initializable{

    @FXML
    private ChoiceBox<String> difficulty;

    @FXML
    private ChoiceBox<String> character;

    @FXML
    private TextField name;

    private String[] characterSelections = {"Yellow", "Gray", "Magenta"};
    private String[] difficultySelections = {"Easy", "Medium", "Hard"};

    private Image yellowPacman = new Image(new FileInputStream("./src/main/java/com/group64/Images/pacmanYellow.png"));
    private Image grayPacman = new Image(new FileInputStream("./src/main/java/com/group64/Images/pacmanGray.png"));
    private Image magentaPacman = new Image(new FileInputStream("./src/main/java/com/group64/Images/pacmanMagenta.png"));


    public SettingsControllerClass() throws FileNotFoundException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        character = new ChoiceBox<>();
//        character.getItems().addAll(characterSelections);
//        difficulty = new ChoiceBox<>();
//        difficulty.getItems().addAll(difficultySelections);
//        name = new TextField();
    }
}
