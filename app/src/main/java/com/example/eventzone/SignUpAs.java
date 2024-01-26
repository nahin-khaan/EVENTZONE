package com.example.eventzone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpAs extends AppCompatActivity {
    LinearLayout linearLayout;
    Animation animate;
    Button userBtn, eventPlannerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_as);
        //hide tittle bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//hide action bar
        getSupportActionBar().hide();
        //hide tittle bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //hide action bar
        getSupportActionBar().hide();
        userBtn = findViewById(R.id.userBtnId);
        eventPlannerBtn = findViewById(R.id.organizerButtonId);
        linearLayout=findViewById(R.id.ballonSignAsId);
        //For animation
        animate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
        linearLayout.startAnimation(animate);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.userBtnId) {

            Intent intent = new Intent(SignUpAs.this, MainActivity.class);
            startActivity(intent);


        }
        if (v.getId() == R.id.organizerButtonId) {
            Intent intent = new Intent(SignUpAs.this, CategorySelect.class);
            startActivity(intent);

        }

    }


}