package com.alex.homeworkplanner;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Title extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                titleToHome();
            }
        };

        handler.postDelayed(runnable,7000);
    }

    public void titleToHome(){
        Intent intent = new Intent(this,MainPage.class);
        startActivity(intent);
    }



}
