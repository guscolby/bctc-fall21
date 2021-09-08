package com.epre.java2.timesleptestimate;

//import javafx.application.Application;
import javafx.application.Platform;
//import javafx.stage.Stage;
//import javafx.scene.*;
import javafx.event.ActionEvent;
//import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;

public class TimeSleptEstimate extends HBox {

	Label nameInputLabel, ageInputLabel, hoursInputLabel;           //labels for user input fields
	TextField nameField, ageField, hoursField;                      //user input fields
	short age;           //variable to store user age
	float hoursInput;    //variable to store user input hours/night
	
	float hoursSlept, daysSlept, yearsSlept;                   //variables to store calculated results
	Label hoursSleptLabel, daysSleptLabel, yearsSleptLabel;    //labels for output
	
	final short HOURS_DAY = 24;   //hours in a day
	final short DAYS_YEAR = 365;  //days in a year
	
	public TimeSleptEstimate() {
		
		Font font = new Font(12);  //sets up default font size
		
		//init statements for user input labels and fields ---------------------------------------------------
		
		//init for name label
		nameInputLabel = new Label("Your name: ");
		nameInputLabel.setFont(font);
		
		//init for name field
		nameField = new TextField();
		nameField.setFont(font);
		nameField.setPrefWidth(120);
		nameField.setOnAction(this::processTextFieldAction);
		
		//init for age label
		ageInputLabel = new Label("Your age: ");
		ageInputLabel.setFont(font);
		
		//init for age field
		ageField = new TextField();
		ageField.setFont(font);
		ageField.setPrefWidth(36);
		ageField.setOnAction(this::processTextFieldAction);
		
		//init for hours/night label
		hoursInputLabel = new Label("How many hours you usually sleep/night: ");
		hoursInputLabel.setFont(font);
		
		//init for hours/night field
		hoursField = new TextField();
		hoursField.setFont(font);
		hoursField.setPrefWidth(36);
		hoursField.setOnAction(this::processTextFieldAction);
		
		
		//declaration/inits for gridpane to house user input labels and fields --------------------------------
		
		GridPane inputPane = new GridPane();
		
		//name and nameField on top
		inputPane.add(nameInputLabel, 0, 0);
		inputPane.add(nameField, 1, 0);
		
		//age and ageField in the middle
		inputPane.add(ageInputLabel, 0, 1);
		inputPane.add(ageField, 1, 1);
		
		//hours slept and hoursField on bottom
		inputPane.add(hoursInputLabel, 0, 2);
		inputPane.add(hoursField, 2, 2);
		
		
		//declaration/init for calculate and exit buttons ----------------------------------------------------
		
		Button calc = new Button("Calculate");
		calc.setOnAction(this::processCalcButtonAction);
		
		Button exit = new Button("Exit");
		exit.setOnAction(this::processExitButtonAction);
		
		
		//init statements for result labels -------------------------------------------------------------------
		
		//init for hours slept result
		hoursSleptLabel = new Label("Total hours slept: --");
		hoursSleptLabel.setFont(font);
		
		//init for days slept result
		daysSleptLabel = new Label("Total days slept: --");
		daysSleptLabel.setFont(font);
		
		//init for years slept result
		yearsSleptLabel = new Label("Total years slept: --");
		yearsSleptLabel.setFont(font);
		
		
		//declaration/inits for hbox/vbox to house result output labels ----------------------------------------
		
		HBox labelBox = new HBox(24);
		labelBox.getChildren().addAll(hoursSleptLabel, daysSleptLabel, yearsSleptLabel);
		
		HBox buttonBox = new HBox(36);
		buttonBox.getChildren().addAll(calc, exit);
		
		//puts result labels and calculation button in a larger vbox
		VBox outputBox = new VBox(48);
		outputBox.getChildren().addAll(buttonBox, labelBox);
	
		
		//set up main hbox contents ----------------------------------------------------------------------------
		getChildren().addAll(inputPane, outputBox);
		
	}
	
	public void processTextFieldAction(ActionEvent event) {
		
		//processes text field input when the enter key is pressed
		//this method stores the user's input age or hours slept/night in an appropriate variable
		
		if ((event.getSource() == ageField) && (ageField.getText().length() > 0)) {             //checks that ageField's input is not blank
			age = Short.parseShort(ageField.getText());
		} else if ((event.getSource() == hoursField) && (hoursField.getText().length() > 0)) {  //checks that hoursField's input is not blank
			hoursInput = Float.parseFloat(hoursField.getText());
		}
		
	}
	
	public void processCalcButtonAction(ActionEvent event) {
		
		//calculates program output when the calc button is pressed
		//this method will also update output labels accordingly
		if ((age != 0) && (hoursInput != 0)) {   //checks that age and hours slept/night are not null
			
			//calculates and sets result variables
			hoursSlept = (age * DAYS_YEAR * hoursInput);
			daysSlept = (hoursSlept / HOURS_DAY);
			yearsSlept = (daysSlept / DAYS_YEAR);
			
			//updates output labels
			hoursSleptLabel.setText("Total hours slept: " + (float)Math.round(hoursSlept * 100d) / 100d);   //rounds hoursSlept to two decimal places
			daysSleptLabel.setText("Total days slept: "   + (float)Math.round(daysSlept * 100d)  / 100d);   //rounds daysSlept to two decimal places
			yearsSleptLabel.setText("Total years slept: " + (float)Math.round(yearsSlept * 100d) / 100d);   //rounds yearsSlept to two decimal places
			
		}
		
	}
	
	public void processExitButtonAction(ActionEvent event) {
		
		//exits program when the exit button is pressed
		Platform.exit();
		
	}
	
 
}
