package com.example.va.LRF;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.Validator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.va.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements Validator {

    EditText name, email, phone, pass, cpass;
    Button reg;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseApp.initializeApp(this);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        cpass = findViewById(R.id.cpass);
        reg = findViewById(R.id.send_button);
        progressBar = findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();

        progressBar.setVisibility(View.INVISIBLE);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gemail = email.getText().toString().trim();
                String gpass = pass.getText().toString().trim();
                String gnum = phone.getText().toString();
                String gcpass = cpass.getText().toString();
                String gname = name.getText().toString();

                if (TextUtils.isEmpty(gemail)){
                    email.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(gcpass)){
                    cpass.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(gname)){
                    name.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(gpass)){
                    pass.setError("Password is Required");
                    return;
                }

                if (TextUtils.isEmpty(gnum)){
                    phone.setError("Password is Required");
                    return;
                }

                if (gpass.length() < 6 ){
                    pass.setError("Password must be >= 6");
                    return;
                }

                if (gcpass.length() < 6 ){
                    cpass.setError("Password must be >= 6");
                    return;
                }

                if (gname.length() < 4 ){
                    name.setError("Name must be >= 4");
                    return;
                }

                if (gnum.length() != 10){
                    phone.setError("Please Enter Valid number");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(gemail,gpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register.this, "Registered", Toast.LENGTH_SHORT).show();
                            if (fAuth.getCurrentUser() != null){
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                        }else {
                            Toast.makeText(Register.this, "Error - "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("Ajju", task.getException().getMessage());
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
    }

    public void reg(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("AjjuMA", "start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("AjjuMA", "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AjjuMA", "destroy");
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AjjuMA", "pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AjjuMA", "resume");
    }
}
