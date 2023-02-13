package com.example.coup;

public class Player {
    private PersonalBank personalBank;
    private Card[] cards = new Card[2];
    public Player(PersonalBank personalBank,Card card1,Card card2){
        this.personalBank = personalBank;
        this.cards[0] = card1;
        this.cards[1] = card2;
    }
}
