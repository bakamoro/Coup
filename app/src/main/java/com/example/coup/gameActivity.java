package com.example.coup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class gameActivity extends AppCompatActivity {

    static String game_name;
    static int myPlayerNumber;
    static String myPlayerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        FileHelper fileHelper = new FileHelper();
        myPlayerName = fileHelper.readFromFile(getApplicationContext());

        game_name = (getIntent().getStringExtra("game_name"));
        myPlayerNumber =  (getIntent().getIntExtra("myPlayerNumber",myPlayerNumber));

    }

}