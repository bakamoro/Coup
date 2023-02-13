package com.example.coup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class waitingRoom extends AppCompatActivity {

    ProgressBar progressBar;
    int count = 0;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_romm);
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
}