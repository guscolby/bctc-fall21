package com.epre.java2.simon;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimonGameLauncher extends Application {

    public void start(Stage primaryStage) {

        //sets up stage and displays it

        SimonGame main = new SimonGame();
        main.setAlignment(Pos.CENTER);
        main.setSpacing(10);
        main.setPadding(new Insets(15));

        //setup scene
        Scene scene = new Scene(main, 500,500);

        //setup stage
        primaryStage.setTitle("Simon Game");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);

    }

}