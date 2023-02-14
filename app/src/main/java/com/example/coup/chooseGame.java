package com.example.coup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.LinkedList;

public class chooseGame extends AppCompatActivity {

    String allGamesNames[],allGamesNamesTemp[];
    LinkedList<String> all = new LinkedList<String>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);
        listView = (ListView) findViewById(R.id.listView1);
        SetAll();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(allGamesNames[0] != "there are no available games") {
                    findGameDetails(allGamesNames[position]);
                }
            }
        });
    }

    private void SetAllGamesNames() {
        allGamesNamesTemp = new String[all.size()];
        for (int i = 0;all.size()>0;i++){
            String game_name = all.pop();
            isGameFull(game_name,i);
        }
    }

    private void SetAll(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Coup games")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                all.add(document.getId());
                            }
                            SetAllGamesNames();
                        } else {
                        }
                    }
                });
    }
    private void moveToGame(String game_name, int num_of_players, Game game) {
        FireStoreHelper fireStoreHelper = new FireStoreHelper(game_name, getApplicationContext());
        fireStoreHelper.updateNumber_of_players(num_of_players,game);

        Intent intent = new Intent(chooseGame.this, waitingRoom.class);;
        if(num_of_players == game.getRequire_player_num()){
            gameActivity gameActivity = new gameActivity();
            gameActivity.game = (game);
            intent = new Intent(chooseGame.this, gameActivity.class);
        }
        intent.putExtra("game_name", game_name);
        intent.putExtra("number of players", num_of_players);
        startActivity(intent);
        finish();
    }
    private void findGameDetails(String game_name){
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
                                }
                            }
                        });
//                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    }
                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
    private void isGameFull(String game_name,int i){
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
                                    allGamesNamesTemp[i] = game_name;
                                    insertToallGamesNames(allGamesNamesTemp,i+1);
                                    ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(chooseGame.this,R.layout.activity_list_view,R.id.textView,allGamesNames);
                                    listView.setAdapter(stringArrayAdapter);
                                }
                            }

                        });
//                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    }
                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }

    private void insertToallGamesNames(String[] allGamesNamesTemp, int i) {
        allGamesNames= new String[(i)];
        for (int j = 0;j<i;j++){
            allGamesNames[j] = allGamesNamesTemp[j];
        }
    }
}