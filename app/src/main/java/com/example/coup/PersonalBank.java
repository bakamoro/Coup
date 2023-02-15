package com.example.coup;

public class PersonalBank {
    private Coins coins = new Coins(R.drawable.pocker_chip,2);
    public PersonalBank(){

    }
    public void kill(){

    }
    public void addCoins(int num){
        coins.addCoins(num);
    }

    public Coins getCoins() {
        return coins;
    }
}
