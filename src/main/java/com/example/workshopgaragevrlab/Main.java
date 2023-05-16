package com.example.workshopgaragevrlab;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    Monster mob = new Monster("débilus", 1, 20, 20, Types.FIRE);
    Monster playableMob = new Monster("débilusPlayer", 1, 20, 20, Types.WATER);

    @Override
    public void start(Stage stage) throws Exception {
        Battle fight = new Battle(playableMob,mob);
        fight.initBattle();
    }
}