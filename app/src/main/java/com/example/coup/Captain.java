package com.example.coup;

public class Captain extends Card{
    public Captain(int resId, CardType type) {
        super(resId, type);
    }

    public Captain() {
        super(R.drawable.captain,CardType.Captain);
    }

    @Override
    public void attack(){

    }
    @Override
    public void defend(){
    }
}
