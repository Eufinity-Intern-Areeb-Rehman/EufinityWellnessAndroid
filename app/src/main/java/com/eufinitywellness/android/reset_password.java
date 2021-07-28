//Eufinity Wellness
//Script Written by Areeb Rehman on 7/27/21
//Usage: Validates the email is in our firebase database and then sends them an email to reset their password

package com.eufinitywellness.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.eufinitywellness.android.CustomElements.EufinityThemeButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class reset_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setNavigationBarColor(Color.BLACK);

        //Creates the firebase authentication object we need to send the reset link
        FirebaseAuth fAuth = FirebaseAuth.getInstance();

        //Creates the button we need for the view
        EufinityThemeButton b = findViewById(R.id.enter);

        //Sets an onClickListener to the button
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gets the data from the email and confirm email fields
                EditText email = findViewById(R.id.email);
                EditText confirmEmail = findViewById(R.id.confirmEmail);

                if(!email.getText().toString().equals(confirmEmail.getText().toString()))
                {
                    email.setError("Both email fields need to match");
                    return;
                }

                fAuth.sendPasswordResetEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(reset_password.this, "Reset link has been sent to your email", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(reset_password.this, "Sorry but you're account doesn't exist", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}