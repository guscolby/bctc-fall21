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

/*----------------------------------------------------------------------------------------------------------------------
> august colby
> 9/8/2021
> krishna nandanoor
> java ii

> a program which displays a simple memory game proof-of concept.
------------------------------------------------------------------------------------------------------------------------
 */

public class SimonGame extends VBox {

    Label instructionsLabel, gameHistoryLabel;

    Button redButton, blueButton, greenButton, yellowButton;

    Button newGameButton, rememberGameButton, exitButton;


    //set game length
    final short GAME_LENGTH = 8;

    //arrays to store button press history in int and string form, respectively
    int[] buttonHistoryIntsArray = new int[GAME_LENGTH];
    String[] buttonHistoryStringArray = new String[GAME_LENGTH];

    //index for those arrays
    short buttonHistoryIndex = 0;


    AudioClip[] audioClips;

    AudioClip current;

    /*------------------------------------------------------------------------------------------------------------------
    > displays a gui with four colored game buttons and several context labels/buttons.

    > instructs the user to input any combination of eight game button presses.
    > when prompted, the program recalls the last "game" (last eight presses)
        and relays them back to the user.
    --------------------------------------------------------------------------------------------------------------------
     */

    public SimonGame () {

        //set font sizing
        Font font      = new Font(12);
        Font titleFont = new Font(14);

        //set button sizing
        final short COLOR_BUTTON_WIDTH  = 100;
        final short COLOR_BUTTON_HEIGHT = 50;


        //instruction/title label
        instructionsLabel = new Label("Welcome to Simon! " +
                "\n\nThis game will remember the order in which you click the colored buttons." +
                "\nClick 'New Game' to get started or 'Remember Game' to print the last 8 buttons clicked.");
        instructionsLabel.setFont(titleFont);
        instructionsLabel.setTextAlignment(TextAlignment.CENTER);

        gameHistoryLabel = new Label("-- No game history --");
        gameHistoryLabel.setFont(font);


        //color button gridpane
        GridPane colorButtonPane = new GridPane();
        colorButtonPane.setAlignment(Pos.CENTER);
        colorButtonPane.setVgap(5);
        colorButtonPane.setHgap(5);

        //red button
        redButton = new Button("Red (0)");
        redButton.setOnAction(this::processColorButton);

        redButton.setPrefWidth(COLOR_BUTTON_WIDTH);
        redButton.setPrefHeight(COLOR_BUTTON_HEIGHT);
        redButton.setFont(titleFont);
        redButton.setStyle("-fx-background-color: tomato");

        //blue button
        blueButton = new Button("Blue (1)");
        blueButton.setOnAction(this::processColorButton);

        blueButton.setPrefWidth(COLOR_BUTTON_WIDTH);
        blueButton.setPrefHeight(COLOR_BUTTON_HEIGHT);
        blueButton.setFont(titleFont);
        blueButton.setStyle("-fx-background-color: deepskyblue");

        //green button
        greenButton = new Button("Green (2)");
        greenButton.setOnAction(this::processColorButton);

        greenButton.setPrefWidth(COLOR_BUTTON_WIDTH);
        greenButton.setPrefHeight(COLOR_BUTTON_HEIGHT);
        greenButton.setFont(titleFont);
        greenButton.setStyle("-fx-background-color: limegreen");

        //yellow button
        yellowButton = new Button("Yellow (3)");
        yellowButton.setOnAction(this::processColorButton);

        yellowButton.setPrefWidth(COLOR_BUTTON_WIDTH);
        yellowButton.setPrefHeight(COLOR_BUTTON_HEIGHT);
        yellowButton.setFont(titleFont);
        yellowButton.setStyle("-fx-background-color: gold");

        //fill color button gridpane
        colorButtonPane.add(redButton,    0,0);
        colorButtonPane.add(blueButton,   1,0);
        colorButtonPane.add(greenButton,  0,1);
        colorButtonPane.add(yellowButton, 1,1);


        /*--------------------------------------------------------------------------------------------------------------
        > resource folder currently not being read.
        > if functional, commented-out file and clip array inits would pull files from /resources/audio/ instead of
            requiring the full path.
        > pulling files from the resource folder also requires that they be pulled as a file input stream instead of
            simply a file, hence the difference in data type in the first array and the conversion to file with
            getFile() in the for loop.

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

        //audio File array (imports raw audio files into program)
        File[] audioFiles = {
                new File("./resources/audio/Red.wav"),
                new File("./resources/audio/Blue.wav"),
                new File("./resources/audio/Green.wav"),
                new File("./resources/audio/Yellow.wav"),
        };

        //AudioClip array (stores usable audio clips)
        audioClips = new AudioClip[audioFiles.length];

        for(int i = 0; i < audioClips.length; i++) {
            audioClips[i] = new AudioClip(audioFiles[i].toURI().toString());
        }

        //default value for current clip (program breaks w/o default)
        current = audioClips[0];


        //menu button box
        HBox menuButtonBox = new HBox(5);
        menuButtonBox.setAlignment(Pos.CENTER);

        newGameButton = new Button("New Game");
        newGameButton.setOnAction(this::processNewGameButton);
        newGameButton.setFont(font);

        rememberGameButton = new Button("Remember Game");
        rememberGameButton.setOnAction(this::processRememberGameButton);
        rememberGameButton.setFont(font);

        exitButton = new Button("Exit Game");
        exitButton.setOnAction(this::processExitButton);
        exitButton.setFont(font);

        //fill menu button box
        menuButtonBox.getChildren().addAll(newGameButton, rememberGameButton, exitButton);


        //initial button states
        redButton.setDisable(true);
        blueButton.setDisable(true);
        greenButton.setDisable(true);
        yellowButton.setDisable(true);

        rememberGameButton.setDisable(true);


        //add gui contents to main vbox
        getChildren().addAll(
                instructionsLabel,
                gameHistoryLabel,
                colorButtonPane,
                menuButtonBox);

    }

    /*------------------------------------------------------------------------------------------------------------------
    > processes a color/game button being clicked.

    > this method:
        > plays the audio clip corresponding to the button's color
        > updates the game's history with an entry corresponding to the button's color
        > checks if the GAME_LENGTH has been reached and ends it if so
            > will also update button states accordingly
    --------------------------------------------------------------------------------------------------------------------
     */

    public void processColorButton(ActionEvent event) {

        current.stop();

        //store index of source button for later use
            //this is accomplished by mapping color buttons to an array and then checking source's index
        Button[] buttonContainerArray =  {redButton, blueButton, greenButton, yellowButton};
        short sourceIndex = (short)(Arrays.asList(buttonContainerArray).indexOf(event.getSource()));

        current = audioClips[sourceIndex];
        current.play();


        if (buttonHistoryIndex < GAME_LENGTH) {                        //checks if game length has been reached

            buttonHistoryIntsArray[buttonHistoryIndex] = sourceIndex;  //add entry to int[] history array

            switch (sourceIndex) {                                     //add entry to String[] history array
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

            buttonHistoryIndex++;

        }

        if (buttonHistoryIndex == GAME_LENGTH) {

            redButton.setDisable(true);
            blueButton.setDisable(true);
            greenButton.setDisable(true);
            yellowButton.setDisable(true);

            rememberGameButton.setDisable(false);

        }

    }

    /*------------------------------------------------------------------------------------------------------------------
    > processes the 'new game' button being clicked.

    > this method:
        > enables the color/game buttons
        > disables the 'remember game' button
        > resets the game history
    --------------------------------------------------------------------------------------------------------------------
     */

    public void processNewGameButton(ActionEvent event) {

        current.stop();

        redButton.setDisable(false);
        blueButton.setDisable(false);
        greenButton.setDisable(false);
        yellowButton.setDisable(false);

        rememberGameButton.setDisable(true);


        Arrays.fill(buttonHistoryIntsArray, 0);
        Arrays.fill(buttonHistoryStringArray, null);

        buttonHistoryIndex = 0;

    }

    /*------------------------------------------------------------------------------------------------------------------
    > processes the 'remember game' button being clicked.

    > updates the game history label with the colors of the last 8 buttons the user clicked.
    --------------------------------------------------------------------------------------------------------------------
     */

    public void processRememberGameButton(ActionEvent event) {

        gameHistoryLabel.setText("");

    for (String color : buttonHistoryStringArray) {
            gameHistoryLabel.setText(gameHistoryLabel.getText() + "  " + color);
        }

    }

    /*------------------------------------------------------------------------------------------------------------------
    > processes the 'exit' button being clicked; exits the program.
    --------------------------------------------------------------------------------------------------------------------
     */

    public void processExitButton(ActionEvent event) {

        Platform.exit();

    }

}
