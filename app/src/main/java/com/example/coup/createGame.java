package com.example.coup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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
            isGameExist();
        }
        else Toast.makeText(this,"please enter game's name",Toast.LENGTH_SHORT).show();
    }

    private void isGameExist() {
        TextView textView = findViewById(R.id.gameNameText);
        String game_name = textView.getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("chess games").document(game_name);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Toast.makeText(createGame.this, "game is already exist", Toast.LENGTH_SHORT).show();

                        //TODO: להזיז את הקוד לjoin
//                        if((int)document.get("number of player") < 4) {
//                            if(document.get("number of player") == null){
//                                moveToGame(1);
//                            }
//                            moveToGame((int)document.get("number of player")+1);
//                        }
//                        else Toast.makeText(createGame.this,"game is full",Toast.LENGTH_LONG).show();
                    }
                    else {
                        moveToGame();
                    }
                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    private void moveToGame() {
        FireStoreHelper fireStoreHelper = new FireStoreHelper(game_name, getApplicationContext());
        Game game = new Game();
        gameActivity gameActivity = new gameActivity();
        gameActivity.game = (game);
        fireStoreHelper.startFireStore(4, game);

        Toast.makeText(this, "game created", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(createGame.this, waitingRoom.class);
        intent.putExtra("game_name", game_name);
        intent.putExtra("number of players", 1);
        startActivity(intent);
        finish();
    }
}