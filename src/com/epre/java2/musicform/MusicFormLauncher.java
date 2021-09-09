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
//import java.io.InputStream;

public class MusicFormLauncher extends Application {

    public void start(Stage primaryStage) throws Exception {

        //start method, sets up stage and displays it

        /*--------------------------------------------------------------------------------------------------------------
        resource folder currently not being read. if functional, commented-out fileinputstream and image declarations
        and inits would pull file from /resources/images/ instead of requiring the full path. pulling images from the
        resource folder requires fewer changes than pulling audio clips, so the only difference here is the addition
        of the getResourceAsStream call. might need to add .getClassLoader(), see SimonGame for reference

        //read header image file
        FileInputStream headerIS = new FileInputStream(getClass().getResourceAsStream("images/badge10.png"));
        Image header = new Image(headerIS)
        ----------------------------------------------------------------------------------------------------------------
        */

        //read header image file
        FileInputStream headerIS = new FileInputStream("./resources/images/badge10.png");
        Image header = new Image(headerIS);

        //set up view for header image
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
