package com.example.coup;

public class Ambassador extends Card{
    public Ambassador(int resId, CardType type) {
        super(resId, type);
    }

    public Ambassador() {
        super(R.drawable.ambassador,CardType.Ambassador);
    }

    @Override
    public void attack(){

    }
    @Override
    public void defend(){
    }
}
