package com.example.coup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class gameActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        CoupView coupView = new CoupView(this);
//        coupView.setGame_name(getIntent().getStringExtra("game_name"));
        coupView.setGame_name("t");
        coupView.setMyPlayerName(getIntent().getStringExtra("MyPlayerName"));
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}