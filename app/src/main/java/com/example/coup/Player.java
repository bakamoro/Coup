package com.example.coup;

public class Player {
    private PersonalBank personalBank;
    private String NameOfPlayer;
    private Card[] cards = new Card[4];
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

    public void addCards(Card[] cards) {
        this.cards[2] = cards[0];
        this.cards[3] = cards[1];
    }

    public PersonalBank getPersonalBank() {
        return personalBank;
    }

    public void setPersonalBank(PersonalBank personalBank) {
        this.personalBank = personalBank;
    }

    public String getNameOfPlayer() {
        return NameOfPlayer;
    }

    public void setNameOfPlayer(String nameOfPlayer) {
        NameOfPlayer = nameOfPlayer;
    }
}
