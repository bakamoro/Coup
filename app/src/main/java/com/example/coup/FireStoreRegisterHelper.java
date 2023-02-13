package com.example.coup;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Source;

import java.util.HashMap;
import java.util.Map;

public class FireStoreRegisterHelper {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String collectionPath = "Coup names", documentPath = "users names";
    private Context context;
    public FireStoreRegisterHelper(Context context){
        this.context = context;
    }
    public boolean addUser(String user){
        DocumentReference docRef = db.collection(collectionPath).document(documentPath);

// Source can be CACHE, SERVER, or DEFAULT.
        Source source = Source.SERVER;

// Get the document, forcing the SDK to use the offline cache
        docRef.get(source).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    // Document found in the offline cache
                    DocumentSnapshot document = task.getResult();


                    if(document.getData().get(user) == null){
                        Map<String, Object> data = new HashMap<>();
                        data.put(user, true);
                        //TODO: addMove -> data
                        db.collection(collectionPath).document(documentPath).set(data, SetOptions.merge());
                        saveUser(user);
                    }
                    else Toast.makeText(context, "name is already exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        if(userExist(user)) {
//            Map<String, Object> data = new HashMap<>();
//            data.put(user, true);
//            //TODO: addMove -> data
//            db.collection(collectionPath).document("user's names").set(data, SetOptions.merge());
//            return true;
//        }
        return false;


    }
    private void saveUser(String user) {
        FileHelper fileHelper = new FileHelper();
        fileHelper.writeToFile(user,context);
    }
}
