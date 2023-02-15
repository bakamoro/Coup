package com.example.coup;

public class Ambassador extends Card{
    public Ambassador(int resId, CardType type) {
        super(resId, type);
    }

    public Ambassador() {
        super(R.drawable.ambassador,CardType.Ambassador);
    }

    @Override
    public void attack(Game game,int myPlayerNumber,int victimPlayerNumber){
        game.setLastAction(Actions.ambassador_block_steal_2_coins);
    }
    @Override
    public void defend(Game game,int myPlayerNumber){
        game.getPlayer(myPlayerNumber).addCards(game.TakeTwoCards());
        game.setLastAction(Actions.ambassador_take_2_cards);
    }
}
