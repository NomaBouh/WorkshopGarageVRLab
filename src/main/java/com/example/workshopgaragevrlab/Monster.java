package com.example.workshopgaragevrlab;

public class Monster {
    private String name;
    private int level;
    private int hp;
    private int hpMax;
    private Types type;
    private Skill skill;

    public Monster(String name, int level, int hp, int hpMax, Types type){
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.hpMax = hpMax;
        this.type = type;
    }

    public void attack(Monster target){
        target.setHp(target.getHp() - 5);
    }

    public void defend(){
        hp++;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public String getName() {
        return name;
    }
    public int getLevel(){
        return level;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public Types getType() {
        return type;
    }

    public int getHpMax() {
        return hpMax;
    }
}
