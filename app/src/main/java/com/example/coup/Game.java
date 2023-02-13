package com.example.coup;

public class Game {
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Deck deck;
    public Game(Player player1,Player player2,Player player3,Player player4,Deck deck){
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.deck = deck;
    }
}
