package com.example.coup;

public class Duke extends Card{
    public Duke(int resId, CardType type) {
        super(resId, type);
    }

    public Duke() {
        super(R.drawable.duke,CardType.Duke);
    }

    @Override
    public void attack(){

    }
    @Override
    public void defend(){

    }
}
