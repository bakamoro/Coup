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
    public Game(){
        this.deck = new Deck();
        Card[] cards1 = deck.TakeTwoCards()
                ,cards2 = deck.TakeTwoCards()
                ,cards3 = deck.TakeTwoCards()
                ,cards4 = deck.TakeTwoCards();
        PersonalBank personalBank1 = new PersonalBank()
                ,personalBank2 = new PersonalBank()
                ,personalBank3 = new PersonalBank()
                ,personalBank4 = new PersonalBank();

        this.player1  = new Player(personalBank1,cards1[0],cards1[0]);
        this.player2  = new Player(personalBank2,cards2[0],cards2[0]);
        this.player3  = new Player(personalBank3,cards3[0],cards3[0]);
        this.player4  = new Player(personalBank4,cards4[0],cards4[0]);
    }
}