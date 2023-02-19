package com.example.coup;

public class Captain extends Card{
    public Captain(int resId, CardType type) {
        super(resId, type);
    }

    public Captain() {
        super(R.drawable.captain,CardType.Captain);
    }

    @Override
    public void attack(Game game,int myPlayerNumber,int victimPlayerNumber){
        game.setLastAction(Actions.captain_steal_2_coins);

    }
    @Override
    public void defend(Game game,int myPlayerNumber,int victimPlayerNumber){
        game.setLastAction(Actions.captain_block_steal_2_coins);
    }
}
