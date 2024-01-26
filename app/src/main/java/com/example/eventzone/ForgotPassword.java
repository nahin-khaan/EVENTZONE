package com.example.eventzone;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    EditText forgotEmail;
    String stringMail;
    Button forgotBtn, backBtn;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
//hide tittle bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

//hide action bar
        getSupportActionBar().hide();


        //Initialize varivale
        forgotEmail = findViewById(R.id.forgotEmailEditId);
        forgotBtn = findViewById(R.id.forgotBtnId);
        backBtn = findViewById(R.id.backBtnId);
        mAuth = FirebaseAuth.getInstance();

        //Reset Button
        forgotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringMail = forgotEmail.getText().toString().trim();
                if (!TextUtils.isEmpty(stringMail)) {
                    resetPassword();

                } else {
                    forgotEmail.setError("Email field cant be empty");
                }
            }
        });
        //  BackButton
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void resetPassword() {
        mAuth.sendPasswordResetEmail(stringMail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ForgotPassword.this, "Reset Password link has been sent to your resgisterd email", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgotPassword.this, Login.class);
                        startActivity(intent);
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ForgotPassword.this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });

    }
}
