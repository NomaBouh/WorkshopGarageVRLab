package com.example.workshopgaragevrlab;

public class Skill {
    private String name;
    private int power;
    //On peut ajouter d'autre Stats

    public int ember(Monster monster){
        if (monster.getType() == Types.FIRE){
            return (int) (monster.getLevel()*1.5*2);
        }
        return monster.getLevel()*2;
    }

    public int waterSplash(Monster monster){
        if (monster.getType() == Types.WATER){
            return (int) (monster.getLevel()*1.5*2);
        }
        return monster.getLevel()*2;
    }

    public int cutterLeafs(Monster monster){
        if (monster.getType() == Types.LEAF){
            return (int) (monster.getLevel()*1.5*2);
        }
        return monster.getLevel()*2;
    }
}
