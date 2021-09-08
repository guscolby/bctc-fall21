package com.epre.java2.simon;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

import java.io.File;

public class SimonGame extends VBox {

    //label vars
    Label instructionsLabel, gameHistoryLabel;

    //color button vars
    Button redButton, blueButton, greenButton, yellowButton;

    //context button vars
    Button newGameButton, rememberGameButton, exitButton;

    //button press history array (ints)
    int[] buttonHistoryIntsArray = new int[8];

    //index for history array
    int buttonHistoryIndex = 0;
    //final int equal to button press history arraylength
    final int GAME_LENGTH = buttonHistoryIntsArray.length;

    //button press history array (strings)
    String[] buttonHistoryStringArray = new String[8];

    //audioclip array
    AudioClip[] audioClips;

    public SimonGame () {

        //default font
        Font font = new Font(12);

        //instructions label content
        instructionsLabel = new Label("Welcome to Simon! " +
                "\nThis game will remember the order in which you click the color buttons." +
                "\nClick \"New Game\" to get started, or \"Remember Game\" to print the last 8 buttons you clicked.");
        instructionsLabel.setFont(font);

        //declare and init color button gridpane
        GridPane colorButtonPane = new GridPane();
        colorButtonPane.setVgap(5);
        colorButtonPane.setHgap(5);

        //init color buttons: content, onpressaction, and color
        //red
        redButton = new Button("Red (0)");
        redButton.setOnAction(this::processColorButton);

        redButton.setFont(font);
        redButton.setStyle("--fx-background-color: red");

        //blue
        blueButton = new Button("Blue (1)");
        blueButton.setOnAction(this::processColorButton);

        blueButton.setFont(font);
        blueButton.setStyle("--fx-background-color: deepskyblue");

        //green
        greenButton = new Button("Green (2)");
        greenButton.setOnAction(this::processColorButton);

        greenButton.setFont(font);
        greenButton.setStyle("--fx-background-color: limegreen");

        //yellow
        yellowButton = new Button("Yellow (3)");
        yellowButton.setOnAction(this::processColorButton);

        yellowButton.setFont(font);
        yellowButton.setStyle("--fx-background-color: gold");

        //array to house color buttons
        Button[] buttonContainerArray = {redButton, blueButton, greenButton, yellowButton};

        //fill color button gridpane from array
        colorButtonPane.add(buttonContainerArray[0], 0,0);  //red
        colorButtonPane.add(buttonContainerArray[1], 1,0);  //blue
        colorButtonPane.add(buttonContainerArray[2], 0,1);  //green
        colorButtonPane.add(buttonContainerArray[3], 1,1);  //yellow

        //declare and init audio file array (imports raw files into program)
        File[] audioFiles = {
                new File("Red.wav"),     //red audio file
                new File("Blue.wav"),    //blue audio file
                new File("Green.wav"),   //green audio file
                new File("Yellow.wav"),  //yellow audio file
        };

        //init audio clip array from file array
        audioClips = new AudioClip[audioFiles.length];

        for(int i = 0; i < audioClips.length; i++) {
            audioClips[i] = new AudioClip(audioFiles[i].toURI().toString());
        }

        //declare context button hbox
        HBox menuButtonBox = new HBox(5);

        //init context buttons
        newGameButton = new Button("New Game");
        newGameButton.setOnAction(this::processNewGameButton);
        newGameButton.setFont(font);

        rememberGameButton = new Button("Remember Game");
        rememberGameButton.setOnAction(this::processRememberGameButton);
        rememberGameButton.setFont(font);

        exitButton = new Button("Exit Game");
        exitButton.setOnAction(this::processExitButton);
        exitButton.setFont(font);

        //fill context button hbox with buttons
        menuButtonBox.getChildren().addAll(newGameButton, rememberGameButton, exitButton);

        //default behavior:
            //set color controls and remember game to disabled
        redButton.setDisable(true);
        blueButton.setDisable(true);
        greenButton.setDisable(true);
        yellowButton.setDisable(true);

        rememberGameButton.setDisable(true);

        //getchildren and addall to overall vbox
        getChildren().addAll(
                instructionsLabel,
                gameHistoryLabel,
                colorButtonPane,
                menuButtonBox);

    }

    public void processColorButton(ActionEvent event) {

        //variable to store button source
            //i.e. int source = Integer.parseInt(event.getSource())

        //stop currently playing audioclip

        //case switch for audio clips

        //case 0 (red)
            //play corresponding audioclip

        //check that max game length of 8 has not been reached
        //if index int < final int (history array length), continue,
            //else break and exit method

        //case switch to handle actual reaction

        //case 0 (red)
            //update button history int array[index int] with source var's value
            //update button history string array[index int] with corresponding "color"
        //same goes for the other three cases

        //index int++

        //end of if statement - another if statement:
            //if maximum game length is reached (index int = final int) then:
            //set color controls to disabled, remember game button to enabled

    }

    public void processNewGameButton(ActionEvent event) {

        //stop currently playing audioclip

        //set color controls to enabled
        //set remember game button to disabled

        //clear game history int and string arrays
        //reset index int to 0

    }

    public void processRememberGameButton(ActionEvent event) {

        //update previous game label with contents of button history string array

        //probably use a for loop or array's toString method and then update label contents

    }

    public void processExitButton(ActionEvent event) {

        //exit program
        Platform.exit();

    }

}
