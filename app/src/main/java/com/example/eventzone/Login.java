package com.example.eventzone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    LinearLayout linearLayout;
    Animation animate;
    private EditText loginEmail, loginPassword;
    private Button loginBtn;
    private TextView signUp, resetPass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //hide tittle bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

//hide action bar
        getSupportActionBar().hide();


// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


//Initialize Datatype and Variable
        loginEmail = (EditText) findViewById(R.id.loginEmailEditId);
        loginPassword = (EditText) findViewById(R.id.loginPassEditId);
        resetPass=findViewById(R.id.resetPass);
        resetPass.setPaintFlags(resetPass.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        resetPass.setOnClickListener(this);
        signUp = (TextView) findViewById(R.id.logInPageNewSignUpTextIds);
        signUp.setOnClickListener(this);
        loginBtn = (Button) findViewById(R.id.logInBtnId);
        loginBtn.setOnClickListener(this);
        linearLayout=findViewById(R.id.balonLogImage);

//For animation
        animate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in_out);
        linearLayout.startAnimation(animate);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.logInBtnId) {
            userLogin();
        }
        if (v.getId() == R.id.logInPageNewSignUpTextIds) {
            Intent intent = new Intent(Login.this, SignUp.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.resetPass) {
            Intent intent1 = new Intent(Login.this, ForgotPassword.class);
            startActivity(intent1);
            Toast.makeText(this, "Forgot your password", Toast.LENGTH_SHORT).show();
        }
    }


    private void userLogin() {
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();
        //Email validation check
        if (email.isEmpty()) {
            loginEmail.setError("Enter an email address");
            loginEmail.requestFocus();
            return;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginEmail.setError("Enter an valid email");
            loginEmail.requestFocus();
            return;
        }
        //Password validation check
        else if (password.isEmpty()) {
            loginPassword.setError("Enter Password");
            loginPassword.requestFocus();
            return;
        } else if (password.length() < 6) {
            loginPassword.setError("Password must be 6 characters");
            loginPassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Intent intent = new Intent(Login.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(Login.this, "Log in sucessfully", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(Login.this, "Log in Unsucessful", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
