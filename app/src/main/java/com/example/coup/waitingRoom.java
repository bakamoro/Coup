package com.example.coup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Timer;
import java.util.TimerTask;

public class waitingRoom extends AppCompatActivity {



    ProgressBar progressBar;
    String game_name;
    TextView waitOpponent;
    int count = 0;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_romm);
        waitOpponent = findViewById(R.id.waitOpponent);

        game_name = getIntent().getStringExtra("game_name");

        updateMoves();


        progressBar = findViewById(R.id.progress_bar);
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                count++;
                progressBar.setProgress(count);
                if(count == 100){
                    timer.cancel();
                }
            }
        };
        timer.schedule(timerTask,0,100);
    }
    public void updateMoves(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Coup games").document(game_name).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Game game = value.toObject(Game.class);
                if(game.getNum_of_players() == game.getRequire_player_num()){
                    gameActivity gameActivity = new gameActivity();
                    gameActivity.game = game;
                    Intent intent = new Intent(waitingRoom.this,gameActivity.class);
                    intent.putExtra("game_name",game_name);
                    startActivity(intent);
                }
                else{
                    waitOpponent.setText(game.getNum_of_players() + "/4 players");
                }
            }
        });
    }
}