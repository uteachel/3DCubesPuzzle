package com.example.user.a3dcubespuzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class FinalActivity extends AppCompatActivity implements View.OnTouchListener {
FrameLayout fl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        fl = findViewById(R.id.fllay);
        fl.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
       onStop();
       finish();
        Intent intent = new Intent(FinalActivity.this,MainActivity.class);
        startActivity(intent);
        return true;
    }
}
