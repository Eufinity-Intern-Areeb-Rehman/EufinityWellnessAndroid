//Eufinity Wellness
//Script was written by Areeb Rehman on 7/24/21
//Usage: This script encompasses the Login Screen and validates the information with our Firebase database.
//It also handles resetting the password.

package com.eufinitywellness.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eufinitywellness.android.CustomElements.EufinityThemeButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;
import androidx.annotation.NonNull;
public class activity_login extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        setContentView(R.layout.activity_login);



        Slide s = new Slide();
        s.setSlideEdge(Gravity.RIGHT);
        getWindow().setEnterTransition(s);

        firebaseAuth = FirebaseAuth.getInstance();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setNavigationBarColor(Color.BLACK);

        //Declares the buttons that we need to use for the login
        EufinityThemeButton login = findViewById(R.id.enter);
        TextView forgotPassword = findViewById(R.id.forgotPassword);

        //Declares the email and password fields that we need to check when they log in
        EditText emailBox = findViewById(R.id.email);
        EditText passwordBox = findViewById(R.id.password);

        //OnClickListener on the login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //This checks to see if the email and password fields are empty
                if(emailBox.getText().toString().isEmpty()) {
                    emailBox.setError("Email is missing");
                    return;
                }
                if(passwordBox.getText().toString().isEmpty()) {
                    passwordBox.setError("Password is missing");
                    return;
                }


                //This validates the email and password to see if its in our firebase database
                firebaseAuth.signInWithEmailAndPassword(emailBox.getText().toString(), passwordBox.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    //Runs if the username and password match
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(activity_login.this, "Logged In!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    //Runs if the username and password don't match
                    public void onFailure(@NonNull @org.jetbrains.annotations.NotNull Exception e) {
                        Toast.makeText(activity_login.this, "Your username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Sends the user to the reset password screen
                Intent i = new Intent(getApplicationContext(), reset_password.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Check to see if the user has logged in before
        if(FirebaseAuth.getInstance().getCurrentUser() != null)
        {
            //send the user to the dashboard since they have already logged in before (I haven't made the dashboard yet)
        }
    }
}