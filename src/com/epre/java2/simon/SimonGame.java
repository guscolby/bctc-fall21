package com.epre.java2.simon;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import java.util.Arrays;

import java.io.File;
//import java.io.FileInputStream;

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

    //variable to store current audio clip
    AudioClip current;

    public SimonGame () {

        //default font
        Font font = new Font(12);

        //sizing vars
        final double BUTTON_WIDTH = 100;
        final double BUTTON_HEIGHT = 50;

        //instructions label content
        instructionsLabel = new Label("Welcome to Simon! " +
                "\n\nThis game will remember the order in which you click the colored buttons." +
                "\nClick \"New Game\" to get started, or \"Remember Game\" to print the last 8 buttons you clicked.");
        instructionsLabel.setFont(font);
        instructionsLabel.setTextAlignment(TextAlignment.CENTER);

        //init game history label as blank
        gameHistoryLabel = new Label("-- No game history --");
        gameHistoryLabel.setFont(font);

        //declare and init color button gridpane
        GridPane colorButtonPane = new GridPane();
        colorButtonPane.setAlignment(Pos.CENTER);
        colorButtonPane.setVgap(5);
        colorButtonPane.setHgap(5);

        //init color buttons: content, onpressaction, and color
        //red
        redButton = new Button("Red (0)");
        redButton.setOnAction(this::processColorButton);

        redButton.setPrefWidth(BUTTON_WIDTH);
        redButton.setPrefHeight(BUTTON_HEIGHT);
        redButton.setFont(font);
        redButton.setStyle("-fx-background-color: tomato");

        //blue
        blueButton = new Button("Blue (1)");
        blueButton.setOnAction(this::processColorButton);

        blueButton.setPrefWidth(BUTTON_WIDTH);
        blueButton.setPrefHeight(BUTTON_HEIGHT);
        blueButton.setFont(font);
        blueButton.setStyle("-fx-background-color: deepskyblue");

        //green
        greenButton = new Button("Green (2)");
        greenButton.setOnAction(this::processColorButton);

        greenButton.setPrefWidth(BUTTON_WIDTH);
        greenButton.setPrefHeight(BUTTON_HEIGHT);
        greenButton.setFont(font);
        greenButton.setStyle("-fx-background-color: limegreen");

        //yellow
        yellowButton = new Button("Yellow (3)");
        yellowButton.setOnAction(this::processColorButton);

        yellowButton.setPrefWidth(BUTTON_WIDTH);
        yellowButton.setPrefHeight(BUTTON_HEIGHT);
        yellowButton.setFont(font);
        yellowButton.setStyle("-fx-background-color: gold");

        //array to house color buttons
        Button[] buttonContainerArray = {redButton, blueButton, greenButton, yellowButton};

        //fill color button gridpane from array
        colorButtonPane.add(buttonContainerArray[0], 0,0);  //red
        colorButtonPane.add(buttonContainerArray[1], 1,0);  //blue
        colorButtonPane.add(buttonContainerArray[2], 0,1);  //green
        colorButtonPane.add(buttonContainerArray[3], 1,1);  //yellow

        /*--------------------------------------------------------------------------------------------------------------
        resource folder currently not being read. if functional, commented-out file and clip array inits would
        pull files from /resources/audio/ instead of requiring the full path. pulling files from the resource folder
        also requires that they be pulled as a file input stream instead of simply a file, hence the difference in
        data type in the first array and the conversion to file with getFile() in the for loop.

        //declare and init audio fileinputstream array (imports raw files into program)

        FileInputStream[] audioFiles = {
                new FileInputStream(getClass().getClassLoader().getResourceAsStream("audio/Red.wav")),
                new FileInputStream(getClass().getClassLoader().getResourceAsStream("audio/Blue.wav")),
                new FileInputStream(getClass().getClassLoader().getResourceAsStream("audio/Green.wav")),
                new FileInputStream(getClass().getClassLoader().getResourceAsStream("audio/Yellow.wav")),
        };

        audioClips = new AudioClip[audioFiles.length];

        for(int i = 0; i < audioClips.length, i++) {
            audioClips[i] = new AudioClip(audioFiles[i].getFile().toURI().toString());
        }
        ----------------------------------------------------------------------------------------------------------------
        */

        //declare and init audio file array (imports raw files into program)

        File[] audioFiles = {
                new File("./resources/audio/Red.wav"),     //red audio file
                new File("./resources/audio/Blue.wav"),    //blue audio file
                new File("./resources/audio/Green.wav"),   //green audio file
                new File("./resources/audio/Yellow.wav"),  //yellow audio file
        };

        //init audio clip array
        audioClips = new AudioClip[audioFiles.length];

        //fill audio clip array from audio file array
        for(int i = 0; i < audioClips.length; i++) {
            audioClips[i] = new AudioClip(audioFiles[i].toURI().toString());
        }

        //default value for current clip
        current = audioClips[0];

        //declare context button hbox
        HBox menuButtonBox = new HBox(5);
        menuButtonBox.setAlignment(Pos.CENTER);

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

        //default behavior: set color controls and remember game to disabled
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

        //stop currently playing audioclip
        current.stop();

        //duplicate of button container array for use in identifying source button
        Button[] buttonEventArray = {redButton, blueButton, greenButton, yellowButton};

        //store index of source button for later use
        short sourceIndex = (short)(Arrays.asList(buttonEventArray).indexOf(event.getSource()));

        //set current audio clip accordingly and play it
        current = audioClips[sourceIndex];
        current.play();

        //check that max game length of 8 has not been reached
        //if index int < final int (history array length), continue, otherwise skip this part
        if (buttonHistoryIndex < GAME_LENGTH) {

            //update game history int array with source button's index
            buttonHistoryIntsArray[buttonHistoryIndex] = sourceIndex;

            //switch updates game history string array with appropriate value
            switch (sourceIndex) {
                case 0:
                    buttonHistoryStringArray[buttonHistoryIndex] = "-Red-";
                    break;
                case 1:
                    buttonHistoryStringArray[buttonHistoryIndex] = "-Blue-";
                    break;
                case 2:
                    buttonHistoryStringArray[buttonHistoryIndex] = "-Green-";
                    break;
                case 3:
                    buttonHistoryStringArray[buttonHistoryIndex] = "-Yellow-";
                    break;
            }

            //increment button history index (basically, add to the total number of presses)
            buttonHistoryIndex++;

        }

        //disable/enable buttons accordingly if max game length has been reached
        if (buttonHistoryIndex == GAME_LENGTH) {

            redButton.setDisable(true);
            blueButton.setDisable(true);
            greenButton.setDisable(true);
            yellowButton.setDisable(true);

            rememberGameButton.setDisable(false);

        }

    }

    public void processNewGameButton(ActionEvent event) {

        //stop currently playing audioclip
        current.stop();

        //set color controls to enabled
        redButton.setDisable(false);
        blueButton.setDisable(false);
        greenButton.setDisable(false);
        yellowButton.setDisable(false);

        //set remember game button to disabled
        rememberGameButton.setDisable(true);

        //clear game history int and string arrays
        Arrays.fill(buttonHistoryIntsArray, 0);
        Arrays.fill(buttonHistoryStringArray, null);

        //reset index int to 0
        buttonHistoryIndex = 0;

    }

    public void processRememberGameButton(ActionEvent event) {

        //update previous game label with contents of button history string array
        gameHistoryLabel.setText("");

    for (String color : buttonHistoryStringArray) {

            gameHistoryLabel.setText(gameHistoryLabel.getText() + "  " + color);

        }

    }

    public void processExitButton(ActionEvent event) {

        //exit program
        Platform.exit();

    }

}
