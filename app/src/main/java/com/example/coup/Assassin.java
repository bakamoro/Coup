package com.example.coup;

public class Assassin extends Card{
    public Assassin(int resId, CardType type) {
        super(resId, type);
    }

    public Assassin() {
        super(R.drawable.assasain,CardType.Assassin);
    }

    @Override
    public void attack(){

    }
    @Override
    public void defend(){
    }
}
