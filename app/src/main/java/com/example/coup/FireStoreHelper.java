package com.example.coup;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class FireStoreHelper {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String game_name;
    private String collectionPath = "Coup games";
    private Context context;

    public FireStoreHelper(String game_name, Context context){//TODO: context = getApllicationContext();
        this.game_name = game_name;
        this.context = context;
    }
    public void startFireStore(int requirePlayersNumber,Game game) {

        Map<String, Object> coupGame = new HashMap<>();
        coupGame.put("require players number",requirePlayersNumber);
        db.collection(collectionPath).document(game_name)
                .set(coupGame)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
//                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context,"couldn't connect to fire store",Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public void addMove(String color,String chessMove){
        Map<String, Object> data = new HashMap<>();
        data.put(color,chessMove);
        //TODO: addMove -> data
        db.collection(collectionPath).document(game_name).set(data, SetOptions.merge());
    }
}
