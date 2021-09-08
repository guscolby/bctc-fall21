package com.epre.java2.musicform;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MusicFormLauncher extends Application {

    public void start(Stage primaryStage) throws FileNotFoundException {

        //start method, sets up stage and displays it

        //read header image file
        Image header = new Image(new FileInputStream("./src/com/epre/java2/musicform/badge10.png"));

        //set up view for image file
        ImageView headerView = new ImageView(header);

        //create and setup MusicForm instance
        MusicForm mainBox = new MusicForm();
        mainBox.setAlignment(Pos.CENTER_LEFT);
        mainBox.setSpacing(5);

        //setup vbox to contain header image view and MusicForm instance
        VBox main = new VBox();
        main.getChildren().addAll(headerView, mainBox);
        main.setAlignment(Pos.CENTER);
        main.setSpacing(10);
        mainBox.setPadding(new Insets(15));

        //setup scene
        Scene scene = new Scene(main, 500, 400);

        //setup stage and show it
        primaryStage.setTitle("Concert Booking");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);

    }

}
