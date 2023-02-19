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
        if(game.getPlayer(myPlayerNumber).getPersonalBank().getCoins().getNumber()>1){
            game.getPlayer(myPlayerNumber).getPersonalBank().getCoins().addCoins(2);
            game.getPlayer(victimPlayerNumber).getPersonalBank().getCoins().reduceCoins(2);
        }
        else if(game.getPlayer(myPlayerNumber).getPersonalBank().getCoins().getNumber()==1){
            game.getPlayer(myPlayerNumber).getPersonalBank().getCoins().addCoins(1);
            game.getPlayer(victimPlayerNumber).getPersonalBank().getCoins().reduceCoins(1);
        }
    }
    @Override
    public void defend(Game game,int myPlayerNumber,int victimPlayerNumber){
        game.setLastAction(Actions.captain_block_steal_2_coins);
    }
}
