package com.example.va;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    EditText email;
    Button send;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.email);
        send = findViewById(R.id.send_button);
        progressBar = findViewById(R.id.progressBar);

        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();

        progressBar.setVisibility(View.INVISIBLE);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gemail = email.getText().toString().trim();

                if (TextUtils.isEmpty(gemail)){
                    email.setError("Email is required");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.sendPasswordResetEmail(gemail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(), Login.class));
                            Toast.makeText(ForgotPassword.this, "Password reset link sent to registered email", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(ForgotPassword.this, "Error - "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("Ajju", task.getException().getMessage());
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("AjjuF", "start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("AjjuF", "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AjjuF", "pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AjjuF", "resume");
    }
}
