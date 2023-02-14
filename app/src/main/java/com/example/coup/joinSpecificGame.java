package com.example.coup;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class joinSpecificGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_specific_game);
    }

    public void joinGema(View view) {
        TextView textView = findViewById(R.id.gameNameText);
        String game_name = textView.getText().toString();

        if(game_name!= null && !game_name.equals("")) {

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("Coup games").document(game_name);

            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {

                            DocumentReference docRef = db.collection("Coup games").document(game_name);
                            docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    Game game = documentSnapshot.toObject(Game.class);
                                    if (game.getNum_of_players() < game.getRequire_player_num()) {
                                        moveToGame(game_name, (game.getNum_of_players() + 1), game);
                                    } else {
                                        Toast.makeText(joinSpecificGame.this, "game is full", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
//                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        } else {
                            Toast.makeText(joinSpecificGame.this, "there is no such game", Toast.LENGTH_SHORT).show();
                        }
                    } else {
//                    Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        }
        else Toast.makeText(this,"please enter game's name",Toast.LENGTH_SHORT).show();
    }

    private void moveToGame(String game_name, int num_of_players, Game game) {
        FireStoreHelper fireStoreHelper = new FireStoreHelper(game_name, getApplicationContext());
        fireStoreHelper.updateNumber_of_players(num_of_players,game);



        Intent intent = new Intent(joinSpecificGame.this, waitingRoom.class);;
        if(num_of_players == game.getRequire_player_num()){
            gameActivity gameActivity = new gameActivity();
            gameActivity.game = (game);
            intent = new Intent(joinSpecificGame.this, gameActivity.class);
        }
        intent.putExtra("game_name", game_name);
        intent.putExtra("number of players", num_of_players);
        startActivity(intent);
        finish();
    }
}