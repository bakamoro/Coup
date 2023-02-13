package com.example.coup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText textView;
    String user_name;
    private FileHelper fileHelper = new FileHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textView = findViewById(R.id.userNameText);
        if(!fileHelper.readFromFile(getApplicationContext()).equals("")){
            Intent intent = new Intent(this,CreateOrJoinGame.class);
            startActivity(intent);
            finish();
        }
    }

    public void register(View view) {
        if(!fileHelper.readFromFile(getApplicationContext()).equals("")){
            Intent intent = new Intent(this,CreateOrJoinGame.class);
            startActivity(intent);
            finish();
        }
        else {
            user_name = textView.getText().toString();
            if (user_name != null && !user_name.equals("")) {
                FireStoreRegisterHelper fireStoreRegisterHelper = new FireStoreRegisterHelper(getApplicationContext());
                fireStoreRegisterHelper.addUser(user_name);
            } else Toast.makeText(this, "please enter your user name", Toast.LENGTH_SHORT).show();
        }
    }
}