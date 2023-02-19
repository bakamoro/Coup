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
        game.setLastAction(Actions.duke_block_gat_2_coins);

    }
    @Override
    public void defend(Game game,int myPlayerNumber,int victimPlayerNumber){
        game.getPlayer(myPlayerNumber).getPersonalBank().addCoins(3);
        game.setLastAction(Actions.duke_get_3_coins);
    }
}
