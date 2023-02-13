package com.example.coup;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;

public class joinSpecificOrRandomGame extends AppCompatActivity{

    private String game_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_specific_or_random_game);

    }

    public void joinSpecificGame(View view) {
        Intent intent = new Intent(this,joinSpecificGame.class);
        startActivity(intent);
        finish();
    }

    public void JoinRandomGame(View view) {
    }
    private void moveToGame(String Color,String game_name){
    }


}