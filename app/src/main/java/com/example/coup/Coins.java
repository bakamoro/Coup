package com.example.coup;

public class Coins {
    private int resId,Number;

    public Coins(int resId,int Number){
        this.resId = resId;
        this.Number = Number;
    }
    public void addCoins(int add){
        this.Number += add;
    }
    public void reduceCoins(int reduce){
        this.Number -= reduce;
    }
}
