package com.example.coup;

public abstract class Card {
    private CardType type;
    private int resId;
    private boolean expose;
    public Card(int resId,CardType type){
        this.type = type;
        this.resId = resId;
        this.expose = false;
    }
    public CardType getType() {
        return type;
    }

    public int getResId() {
        return resId;
    }

    public boolean getExpose() {
        return expose;
    }

    public void setExpose(boolean expose) {
        this.expose = expose;
    }

    abstract void attack(Game game,int myPlayerNumber);

    abstract void defend(Game game,int myPlayerNumber);

}

