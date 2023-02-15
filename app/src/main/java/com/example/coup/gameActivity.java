package com.example.coup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class gameActivity extends AppCompatActivity {

    static String game_name;
    static int myPlayerNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        game_name = (getIntent().getStringExtra("game_name"));
        myPlayerNumber =  (getIntent().getIntExtra("myPlayerNumber",myPlayerNumber));

    }

}