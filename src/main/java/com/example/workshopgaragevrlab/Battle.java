package com.example.workshopgaragevrlab;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

import java.util.Random;
import java.util.Scanner;

public class Battle {
    private final Monster playerMonster;
    private final Monster opponentMonster;
    private int currentRound;
    private Skill skill;
    private final TextArea console; // TextArea for console-like output
    private String userInput = "";

    public Battle(Monster playerMonster, Monster opponentMonster, TextArea console) {
        this.playerMonster = playerMonster;
        this.opponentMonster = opponentMonster;
        this.console = console;
        this.currentRound = 1;
    }

    public void startBattle() {
        appendText("The Battle begins!");
        appendText("Player's Monster: " + playerMonster.getName());
        appendText("Opponent's Monster: " + opponentMonster.getName());

        while (playerMonster.isAlive() && opponentMonster.isAlive()) {
            appendText("\n--- Round " + currentRound + " ---");

            playerTurn();
            if (!opponentMonster.isAlive()) break;

            opponentTurn();
            if (!playerMonster.isAlive()) break;

            currentRound++;
        }

        endBattle();
    }

    private void endBattle() {
        appendText("\nThe battle has ended!");
        // Determine the winner and display the result
        if (playerMonster.isAlive()) {
            appendText("Player's Pokémon " + playerMonster.getName() + " wins!");
        } else {
            appendText("Opponent's Pokémon " + opponentMonster.getName() + " wins!");
        }
    }

    public void playerTurn() {
        appendText("\nPlayer's Turn:");
        appendText("Choose an action:");
        appendText("1. Attack");
        appendText("2. Defend");
        appendText("3. Use Skill");

        int choice = getUserInput();

        switch (choice) {
            case 1:
                playerMonster.attack(opponentMonster);
                appendText("Player's Monster: " + playerMonster.getName() + " (HP: " + playerMonster.getHp() + "/" + playerMonster.getHpMax() + ")");
                break;
            case 2:
                playerMonster.defend();
                appendText("Player's Monster: " + playerMonster.getName() + " is defending.");
                break;
            case 3:
                useSkill();
                break;
            default:
                appendText("Invalid Selection. Try again.");
                playerTurn();
                break;
        }
    }

    public void useSkill() {
        appendText("Choose a Skill:");
        appendText("1. Ember");
        appendText("2. Water Splash");
        appendText("3. Cutter Leaf");

        getUserInput();
    }

    public void opponentTurn() {
        appendText("\nOpponent's Turn:");

        Random rdm = new Random();
        int ai = rdm.nextInt(1, 4);

        switch (ai) {
            case 1:
                opponentMonster.attack(playerMonster);
                break;
            case 2:
                opponentMonster.defend();
                break;
            case 3:
                appendText("Missed attack.");
                break;
            default:
                break;
        }

        appendText("Monster: " + opponentMonster.getName() + " (HP: " + opponentMonster.getHp() + "/" + opponentMonster.getHpMax() + ")");

        // Wait for a specific event before continuing
        waitForPlayerTurn();
    }

    private void waitForPlayerTurn() {
        // Disable the input until player's turn starts
        console.setEditable(false);

        // Enable the input after a delay to allow the player to read the opponent's action
        PauseTransition delay = new PauseTransition(Duration.seconds(2)); // Adjust the delay duration as needed
        delay.setOnFinished(event -> console.setEditable(true));
        delay.play();
    }

    private int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return choice;
    }


    private void processUserInput(String input) {
        int choice;
        try {
            choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    playerMonster.attack(opponentMonster);
                    appendText("Player's Monster: " + playerMonster.getName() + " (HP: " + playerMonster.getHp() + "/" + playerMonster.getHpMax() + ")");
                    break;
                case 2:
                    playerMonster.defend();
                    appendText("Player's Monster: " + playerMonster.getName() + " is defending.");
                    break;
                case 3:
                    useSkill();
                    break;
                default:
                    appendText("Invalid Selection. Try again.");
                    break;
            }
        } catch (NumberFormatException e) {
            appendText("Invalid input. Please enter a valid number.");
        }
    }



    private void appendText(String text) {
        Platform.runLater(() -> console.appendText(text + "\n"));
    }
}

