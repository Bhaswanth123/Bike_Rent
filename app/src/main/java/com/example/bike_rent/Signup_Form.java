package com.example.bike_rent;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_Form extends AppCompatActivity {

    private EditText name, username, txtemail, txtpasswd, txtcpasswd;
    private Button register;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("Signup Form");

        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        txtemail = findViewById(R.id.email);
        txtpasswd = findViewById(R.id.password);
        txtcpasswd = findViewById(R.id.cnfmpassword);
        register = findViewById(R.id.register);


       firebaseAuth = FirebaseAuth.getInstance();



       register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password, confirmPassword;

                email = txtemail.getText().toString().trim();
                password = txtpasswd.getText().toString().trim();
                confirmPassword = txtcpasswd.getText().toString().trim();

                if (TextUtils.isEmpty(email))
                    Toast.makeText(Signup_Form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(password))
                    Toast.makeText(Signup_Form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(confirmPassword))
                    Toast.makeText(Signup_Form.this, "Passwords should match", Toast.LENGTH_SHORT).show();

                if (password.length() < 6)
                    Toast.makeText(Signup_Form.this, "Password should be longer than 6 characters", Toast.LENGTH_SHORT).show();

                if (password.equals(confirmPassword)) {

                   firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {


                                    if (task.isSuccessful()) {

                                        Toast.makeText(Signup_Form.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Signup_Form.this, Login_form.class));

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Signup_Form.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });

                }


            }
        });


    }
}
