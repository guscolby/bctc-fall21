package com.epre.java2.timesleptestimate;

import com.epre.java2.timesleptestimate.TimeSleptEstimate;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.application.Application;
import javafx.geometry.*;
//import javafx.scene.layout.*;

public class TimeSleptEstimateLauncher extends Application {

	public void start(Stage primaryStage) {
		
		//start method, sets up pane alignment and starts stage
		
		//creates and centers an instance of the TimeSleptEstimate class to be used
		TimeSleptEstimate main = new TimeSleptEstimate();
		main.setAlignment(Pos.CENTER);
		main.setSpacing(50);
		
		//declare/init for the program's scene
		Scene scene = new Scene(main, 1000, 100);
		
		//set up stage and show it
		primaryStage.setTitle("Estimate Time Slept");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}
	
}
