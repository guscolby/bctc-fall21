package com.epre.java2.musicform;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.File;

public class MusicForm extends VBox {


    /*------------------------------------------------------------------------------------------------------------------
    Declaration of variables to be used in the program. This includes:
    - Labels for all fields and buttons
    - Fields for user input
    - Buttons/checkboxes for user interaction
    - Strings to store user-input data
     -------------------------------------------------------------------------------------------------------------------
     */

    Label bandNameLabel, websiteLabel;
    Label sampleTitle, sampleLabel1, sampleLabel2;
    Label daysOfWeekTitle;
    Label contactNameLabel, contactPhoneLabel;

    TextField bandNameField, websiteField;
    TextField contactNameField, contactPhoneField;


    RadioButton sampleButton1, sampleButton2;

    CheckBox dayOfWeekBox1, dayOfWeekBox2, dayOfWeekBox3;

    Button submitButton, clearButton, exitButton;


    String bandName, bandWebsite, daysAvailable, contactName, contactPhone;

    public MusicForm() {

        //font setup
        Font font = new Font(12);

        //init first label/field pair
        bandNameLabel = new Label("Name of Band: ");
        bandNameLabel.setFont(font);

        bandNameField = new TextField();
        bandNameField.setFont(font);
        bandNameField.setPrefWidth(120);
        //bandNameField.setOnAction(this::processFieldInput);

        //declare and init first hbox
        HBox bandNameBox = new HBox(5);
        bandNameBox.getChildren().addAll(bandNameLabel, bandNameField);

        //init second label/field pair
        websiteLabel = new Label("Band website or Facebook page: ");
        websiteLabel.setFont(font);

        websiteField = new TextField();
        websiteField.setFont(font);
        websiteField.setPrefWidth(120);
        //websiteField.setOnAction(this::processFieldInput);

        //declare and init second hbox
        HBox bandWebsiteBox = new HBox(5);
        bandWebsiteBox.getChildren().addAll(websiteLabel, websiteField);

        //init sound clip label
        sampleTitle = new Label("Sample sound clips of songs:");

        //setup toggle group for radio buttons
        ToggleGroup sampleGroup = new ToggleGroup();

        //init radio buttons
        sampleButton1 = new RadioButton();
        sampleButton1.setToggleGroup(sampleGroup);
        sampleButton1.setOnAction(this::processRadioInput);

        sampleButton2 = new RadioButton();
        sampleButton2.setToggleGroup(sampleGroup);
        sampleButton2.setOnAction(this::processRadioInput);

        //init radio button labels
        sampleLabel1 = new Label("Free Bird");
        sampleLabel1.setFont(font);

        sampleLabel2 = new Label("Stairway to Heaven");
        sampleLabel2.setFont(font);

        //declare and init radio button gridpane
        GridPane samplePane = new GridPane();
        samplePane.setVgap(2.5);

        samplePane.add(sampleButton1, 0,0);
        samplePane.add(sampleLabel1, 1,0);

        samplePane.add(sampleButton2, 0,1);
        samplePane.add(sampleLabel2, 1,1);

        //init days of week label
        daysOfWeekTitle = new Label("Days of the week you are typically available for playing " +
                "(check all that apply):");
        daysOfWeekTitle.setFont(font);

        //init checkboxes
        dayOfWeekBox1 = new CheckBox("Tuesday");
        dayOfWeekBox1.setFont(font);

        dayOfWeekBox2 = new CheckBox("Friday");
        dayOfWeekBox2.setFont(font);

        dayOfWeekBox3 = new CheckBox("Saturday");
        dayOfWeekBox3.setFont(font);

        //declare and init checkbox hbox
        HBox dayOfWeekBox = new HBox(5);
        dayOfWeekBox.getChildren().addAll(dayOfWeekBox1, dayOfWeekBox2, dayOfWeekBox3);

        //init contact labels
        contactNameLabel = new Label("Contact Name: ");
        contactNameLabel.setFont(font);

        contactPhoneLabel = new Label("Contact Phone: ");
        contactPhoneLabel.setFont(font);

        //init contact fields
        contactNameField = new TextField();
        contactNameField.setFont(font);
        contactNameField.setPrefWidth(120);
        //contactNameField.setOnAction(this::processFieldInput);

        contactPhoneField = new TextField();
        contactPhoneField.setFont(font);
        contactPhoneField.setPrefWidth(120);
        //contactPhoneField.setOnAction(this::processFieldInput);

        //declare and init contact gridpane
        GridPane contactPane = new GridPane();

        contactPane.add(contactNameLabel, 0,0);
        contactPane.add(contactNameField, 1,0);

        contactPane.add(contactPhoneLabel, 0,1);
        contactPane.add(contactPhoneField, 1,1);

        //init bottom buttons
        submitButton = new Button("Submit Audition");
        submitButton.setOnAction(this::processSubmitButton);

        clearButton = new Button("Clear Form");
        clearButton.setOnAction(this::processClearButton);

        exitButton = new Button("Exit");
        exitButton.setOnAction(this::processExitButton);

        //declare and init bottom hbox
        HBox buttonBox = new HBox(5);
        buttonBox.getChildren().addAll(submitButton, clearButton, exitButton);

        //getchildren and addall to main vbox
        getChildren().addAll(
                bandNameBox, bandWebsiteBox,
                sampleTitle,  samplePane,
                daysOfWeekTitle, dayOfWeekBox,
                contactPane,
                buttonBox);

    }

    public void processRadioInput(ActionEvent event) {

        //(optional) play sound clips when corresponding radio button is pushed

    }

    public void processSubmitButton(ActionEvent event) {

        //processes the submit button being pressed
        Alert outputDialog = new Alert(Alert.AlertType.INFORMATION);
        outputDialog.setHeaderText(null);

        //checks if text fields are empty
        if ((bandNameField.getText().isEmpty())
                || (websiteField.getText().isEmpty())
                || (contactNameField.getText().isEmpty())
                || (contactPhoneField.getText().isEmpty())) {

            //shows error message if any field is empty
            outputDialog.setContentText("Error: one or more fields were left blank.");

        } else {

            //sets variables according to text field content
            bandName = bandNameField.getText();
            bandWebsite = websiteField.getText();

            daysAvailable = "";
            if (dayOfWeekBox1.isSelected()) {
                daysAvailable += "--Tuesday\n";
            }
            if (dayOfWeekBox2.isSelected()) {
                daysAvailable += "--Friday\n";
            }
            if(dayOfWeekBox3.isSelected()) {
                daysAvailable += "--Saturday\n";
            }

            contactName = contactNameField.getText();
            contactPhone = contactPhoneField.getText();

            //sets up info dialog with user-input data
            outputDialog.setContentText("The following information was successfully submitted:" +
                    "\nBand: "            + bandName +
                    "\nWebsite/FB Page: " + bandWebsite +
                    "\nSound clips were successfully uploaded." +
                    "\n" + bandName + " are available to play on:" +
                    "\n" + daysAvailable +
                    "Contact information: " + contactName +
                    " at " + contactPhone);

        }

        //shows dialog
        outputDialog.showAndWait();

    }

    public void processClearButton(ActionEvent event) {

        //clear all fields/buttons
        bandNameField.clear();
        websiteField.clear();

        sampleButton1.setSelected(false);
        sampleButton2.setSelected(false);

        dayOfWeekBox1.setSelected(false);
        dayOfWeekBox2.setSelected(false);
        dayOfWeekBox3.setSelected(false);

        contactNameField.clear();
        contactPhoneField.clear();

    }

    public void processExitButton(ActionEvent event) {

        //processes the exit button being pressed
        Platform.exit();

    }

}
