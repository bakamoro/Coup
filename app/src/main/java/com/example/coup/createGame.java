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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class createGame extends AppCompatActivity {
    EditText textView;
    String game_name;
    private boolean gameIsExist = false;
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
        db.collection("Coup games")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(game_name .equals(document.getId())){
                                    Toast.makeText(createGame.this, "game is already exist", Toast.LENGTH_SHORT).show();
                                    gameIsExist = true;
                                }
                            }
                            if(!gameIsExist) {
                                moveToGame();
                            }
                        } else {
                        }
                    }
                });
    }

    private void moveToGame() {
        FireStoreHelper fireStoreHelper = new FireStoreHelper(game_name, getApplicationContext());
        Game game = new Game();
        fireStoreHelper.startFireStore(4, game);

        Toast.makeText(this, "game created", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(createGame.this, waitingRoom.class);
        intent.putExtra("game_name", game_name);
        intent.putExtra("number of players", 1);
        startActivity(intent);
        finish();
    }
}