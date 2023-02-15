package com.example.coup;

public class Contessa extends Card{
    public Contessa(int resId, CardType type) {
        super(resId, type);
    }

    public Contessa() {
        super(R.drawable.contessa,CardType.Contessa);
    }

    @Override
    void attack(Game game,int myPlayerNumber,int victimPlayerNumber) {

    }

    @Override
    void defend(Game game,int myPlayerNumber) {
        game.setLastAction(Actions.contessa_block_assassin);

    }
}
