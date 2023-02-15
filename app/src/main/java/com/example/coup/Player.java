package com.example.coup;

public class Player {
    private PersonalBank personalBank;
    private Card[] cards = new Card[2];
    private boolean isTurn;
    public Player(PersonalBank personalBank, Card card1, Card card2, boolean isTurn){
        this.personalBank = personalBank;
        this.isTurn = isTurn;
        this.cards[0] = card1;
        this.cards[1] = card2;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public Card[] getCards() {
        return cards;
    }
}
