package com.example.eventzone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    LinearLayout linearLayout;
    Animation animate;
    EditText signEmail, signPass;
    Button signUpBtn;
    TextView alreadySignText;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //hide tittle bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

//hide action bar
        getSupportActionBar().hide();


// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        signEmail = (EditText) findViewById(R.id.signUpEmailEditId);
        signPass = (EditText) findViewById(R.id.signUpPassEditId);
        alreadySignText = (TextView) findViewById(R.id.signUpPageLoginHereTextId);
        alreadySignText.setOnClickListener(this);
        signUpBtn = (Button) findViewById(R.id.signUpBtnId);
        signUpBtn.setOnClickListener(this);
        linearLayout=findViewById(R.id.balonSignImage);

//For animation
        animate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in_out);
        linearLayout.startAnimation(animate);



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signUpBtnId) {
            signupUser();


        }
        if (v.getId() == R.id.signUpPageLoginHereTextId) {
            Intent intent = new Intent(SignUp.this, Login.class);
            startActivity(intent);
        }

    }

    private void signupUser() {
        String email = signEmail.getText().toString().trim();
        String password = signPass.getText().toString().trim();
        //Email validation check
        if (email.isEmpty()) {
            signEmail.setError("Enter an email address");
            signEmail.requestFocus();
            return;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signEmail.setError("Enter an valid email");
            signEmail.requestFocus();
            return;
        }
        //Password validation check
        else if (password.isEmpty()) {
            signPass.setError("Enter Password");
            signPass.requestFocus();
            return;
        } else if (password.length() < 6) {
            signPass.setError("Password must be 6 characters");
            signPass.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mAuth.getCurrentUser().sendEmailVerification();
                    Toast.makeText(SignUp.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, SignUpAs.class);
                    startActivity(intent);


                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(SignUp.this, "This email already registered", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUp.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
    }
}
