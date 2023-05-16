package com.example.workshopgaragevrlab;

import java.util.Random;
import java.util.Scanner;

public class Battle {
    private final Monster playerMonster;
    private final Monster opponentMonster;
    private int currentRound;
    private Skill skill;

    public Battle(Monster playerMonster, Monster opponentMonster){
        this.opponentMonster = opponentMonster;
        this.playerMonster = playerMonster;
        this.currentRound = 1;
    }

    public void initBattle(){
        System.out.println("The Battle begins!");
        System.out.println("Player's Monster: " + playerMonster.getName());
        System.out.println("Opponent's Monster: " + opponentMonster.getName());
        
        while(playerMonster.isAlive() && opponentMonster.isAlive()){
            System.out.println("\n--- Round " + currentRound + " ---");
            
            playerTurn();
            if (!opponentMonster.isAlive()) break;
            
            opponentTurn();
            if (!playerMonster.isAlive()) break;
            
            currentRound++;
        }
        
        endBattle();
    }

    private void endBattle() {
        System.out.println("\nThe battle has ended!");
        // Determine the winner and display the result
        if (playerMonster.isAlive()) {
            System.out.println("Player's Pokémon " + playerMonster.getName() + " wins!");
        } else {
            System.out.println("Opponent's Pokémon " + opponentMonster.getName() + " wins!");
        }
    }

    public void playerTurn(){
        System.out.println("\nPlayer's Turn:");

        System.out.println("Choose an action:");
        System.out.println("1. Attack");
        System.out.println("2. Defends");
        System.out.println("3. UseSkill");

        int choice = getUserInput();

        switch (choice) {
            case 1 -> playerMonster.attack(opponentMonster);
            case 2 -> playerMonster.defend();
            case 3 -> useSkill();
            default -> {
                System.out.println("Invalid Selection. Try again.");
                playerTurn();
            }
        }

        System.out.println("Player's Monster: "+ playerMonster.getName() + " (HP: " + playerMonster.getHp() + "/" + playerMonster.getHpMax());

    }

    public void useSkill(){
        System.out.println("Choose a Skill:");
        System.out.println("1. Ember");
        System.out.println("2. WaterSplash");
        System.out.println("3. CutterLeaf");

        int choice = getUserInput();

        switch (choice) {
            case 1 -> skill.ember(playerMonster);
            case 2 -> skill.waterSplash(playerMonster);
            case 3 -> skill.cutterLeafs(playerMonster);
            default -> {
                System.out.println("Invalid Selection. Try again.");
                useSkill();
            }
        }
    }

    private int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void opponentTurn(){
        System.out.println("\nOpponent's Turn:");
        Random rdm = new Random();
        int ai = rdm.nextInt(1,4);
        if (ai == 1){
            opponentMonster.attack(playerMonster);
        }
        if (ai == 2){
            opponentMonster.defend();
        }
        if (ai == 3){
            System.out.println("Miss attack.");
        }
        System.out.println("Monster: "+ opponentMonster.getName() + " (HP: " + opponentMonster.getHp() + "/" + opponentMonster.getHpMax());

    }
}
