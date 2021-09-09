package com.epre.java2.simon;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*----------------------------------------------------------------------------------------------------------------------
> august colby
> 9/8/2021
> krishna nandanoor
> java ii

> a program which launches the SimonGame gui.
------------------------------------------------------------------------------------------------------------------------
 */

public class SimonGameLauncher extends Application {

    /*------------------------------------------------------------------------------------------------------------------
    > the launcher's start method.

    > creates an instance of SimonGame, adds it to the gui's scene & stage and shows it
    --------------------------------------------------------------------------------------------------------------------
     */

    public void start (Stage primaryStage) {

        SimonGame main = new SimonGame();
        main.setAlignment(Pos.CENTER);
        main.setSpacing(30);
        main.setPadding(new Insets(15));

        Scene scene = new Scene(main, 600,400);

        primaryStage.setTitle("Simon Game");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);

    }

}
