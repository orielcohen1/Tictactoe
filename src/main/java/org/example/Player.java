package org.example;

public class Player {

    private String name;
    private char symbol;
    private int weight;


    public Player(String name, char symbol, int weight) {
        this.name = name;
        this.symbol = symbol;
        this.weight = weight;
    }

    //getters

    public String getName(){
        return this.name;
    }

    public char getSymbol(){
        return this.symbol;
    }

    public int getWeight() {
        return this.weight;
    }


    //setters

    public void setName(String name){
        this.name = name;
    }

    public void setSymbol(char symbol){
        this.symbol = symbol;
    }

}