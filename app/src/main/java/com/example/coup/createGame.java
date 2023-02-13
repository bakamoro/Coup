package com.example.coup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class createGame extends AppCompatActivity {
    EditText textView;
    String game_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        textView = findViewById(R.id.gameNameText);
    }

    public void createGame(View view) {
        game_name = textView.getText().toString();
        if(game_name!= null && !game_name.equals("")) {
//            FireStoreHelper fireStoreHelper = new FireStoreHelper(game_name, getApplicationContext());
//            fireStoreHelper.startFireStore(4);
            Toast.makeText(this,"game created",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(createGame.this,gameActivity.class);
            intent.putExtra("game_name",game_name);
            startActivity(intent);
        }
        else Toast.makeText(this,"please enter game's name",Toast.LENGTH_SHORT).show();
    }
}