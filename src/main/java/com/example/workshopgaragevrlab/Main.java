package com.example.workshopgaragevrlab;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {

    Monster mob = new Monster("débilus", 1, 20, 20, Types.FIRE);
    Monster playableMob = new Monster("débilusPlayer", 1, 20, 20, Types.WATER);
    TextArea console = new TextArea();

    public void start(Stage stage) throws Exception {
        Image background = new Image(getClass().getResourceAsStream("/com/example/workshopgaragevrlab/background.png"));

        BackgroundImage bckImg = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Pane root = new Pane();
        root.setBackground(new Background(bckImg));

        console.setPrefSize(600, 200);
        console.setLayoutX(700);
        console.setLayoutY(400);
        console.setEditable(false);
        root.getChildren().add(console);

        Scene scene = new Scene(root, 1366,768 );
        stage.setScene(scene);
        stage.show();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Start a separate thread to handle user input asynchronously
        executorService.execute(() -> {
            Battle fight = new Battle(playableMob, mob, console); // Pass the console to the Battle class
            fight.startBattle();
        });

        // Shutdown the executor service when the application is closed
        stage.setOnCloseRequest(event -> {
            executorService.shutdownNow();
            Platform.exit();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}