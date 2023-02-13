package com.example.coup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class gameActivity extends AppCompatActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        FileHelper fileHelper = new FileHelper();
        CoupView coupView = new CoupView(this);
        coupView.game_name = (getIntent().getStringExtra("game_name"));
        coupView.myPlayerName = fileHelper.readFromFile(getApplicationContext());
        coupView.game = game;
    }

}