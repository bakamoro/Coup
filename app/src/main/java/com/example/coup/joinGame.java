package com.example.coup;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class joinGame extends AppCompatActivity{

    private boolean moveToGameNotCalled = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_specific_or_random_game);

    }

    public void joinSpecificGame(View view) {
        Intent intent = new Intent(this,joinSpecificGame.class);
        startActivity(intent);

    }

    private void moveToGame(String game_name, int num_of_players, Game game){

        FireStoreHelper fireStoreHelper = new FireStoreHelper(game_name, getApplicationContext());
        fireStoreHelper.updateNumber_of_players(num_of_players,game);



        Intent intent = new Intent(joinGame.this, waitingRoom.class);
        if(num_of_players == game.getRequire_player_num()){
            gameActivity gameActivity = new gameActivity();
            gameActivity.game = (game);
            intent = new Intent(joinGame.this, gameActivity.class);
        }
        intent.putExtra("game_name", game_name);
        intent.putExtra("number of players", num_of_players);
        startActivity(intent);
        finish();
    }

    public void JoinQuickGame(View view) {
        int i = 3;
        for (;i>0;i--) {
            JoinQuickGameByNumber(i);
        }
    }

    private void JoinQuickGameByNumber(int i) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference coupRef = db.collection("Coup games");
        coupRef
                .whereEqualTo("num_of_players", i)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(moveToGameNotCalled){
                                    moveToGame(document.getId(),i+1,document.toObject(Game.class));
                                    moveToGameNotCalled = false;
                                }
//                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        }
                        else {
                        }
                    }
                });
    }

    public void ChooseGame(View view) {
        Intent intent = new Intent(this,chooseGame.class);

    }
}