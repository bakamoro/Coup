package com.example.coup;

public class Contessa extends Card{
    public Contessa(int resId, CardType type) {
        super(resId, type);
    }

    public Contessa() {
        super(R.drawable.contessa,CardType.Contessa);
    }

    @Override
    void attack(Game game,int myPlayerNumber) {

    }

    @Override
    void defend(Game game,int myPlayerNumber) {

    }
}
