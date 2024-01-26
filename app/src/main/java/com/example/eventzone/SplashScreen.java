package com.example.eventzone;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    Animation animate;
    LinearLayout linearLayout;
    ImageView logo, fire1, ballon, fire2;
    TextView appTittle;

    ProgressBar progressBar;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//hide tittle bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

//hide action bar
        getSupportActionBar().hide();


//Data type and Varriable initialize
        linearLayout=(LinearLayout) findViewById(R.id.balonFireworkImage);
        logo = findViewById(R.id.logoId);
        appTittle = findViewById(R.id.appTittleId);
        fire1 = findViewById(R.id.fireOneId);
        ballon = findViewById(R.id.ballonId);
        fire2 = findViewById(R.id.fireTwoId);
        progressBar = findViewById(R.id.progressId);


//Animation Work
        animate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
        logo.startAnimation(animate);
        animate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        linearLayout.startAnimation(animate);


// In Thread class Runnable() method inside use run doWork() method
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        });
        thread.start();

    }
 //doWrok Method  For splash duration Screen

    public void doWork() {
        for (progress = 10; progress <= 100; progress = progress + 50) {
            try {
                Thread.sleep(500);
                progressBar.setProgress(progress);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

//In startApp() funtion work for SplashScreen to Login activity by  using Intent
    public void startApp() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);

    }
}
