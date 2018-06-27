package com.iastate.yummyames.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iastate.yummyames.R;

public class WelcomeActivity extends AppCompatActivity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Handler handler = new Handler();
        handler.postDelayed(this, 3000);
    }
    @Override
    public void run(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        this.finish();
    }

}
