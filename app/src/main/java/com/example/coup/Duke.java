package com.example.coup;

public class Duke extends Card{
    public Duke(int resId, CardType type) {
        super(resId, type);
    }

    public Duke() {
        super(R.drawable.duke,CardType.Duke);
    }

    @Override
    public void attack(Game game,int myPlayerNumber,int victimPlayerNumber){

    }
    @Override
    public void defend(Game game,int myPlayerNumber){
        game.getPlayer(myPlayerNumber).getPersonalBank().addCoins(3);
    }
}
