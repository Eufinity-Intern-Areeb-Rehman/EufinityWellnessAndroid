//Eufinity Wellness
//Script written by Areeb Rehman on 7/26/21
//Usage: This script registers the user if they're a peer

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class activity_registerPeer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_peer);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setNavigationBarColor(Color.BLACK);
        
        //Declares the Firebase objects needed for the script
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference users = rootNode.getReference("Users");
        
        //Declares the objects we need to extract the email, password and other data
        EufinityThemeButton signup = findViewById(R.id.signupbutton);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        EditText confirmPassword = findViewById(R.id.confirmPassword);
        EditText firstName = findViewById(R.id.firstName);
        EditText lastName = findViewById(R.id.lastName);
        EditText dateOfBirth = findViewById(R.id.dateOfBirth);
        EditText gender = findViewById(R.id.gender);

        //Checks to see if the signup button has been pressed
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validates the data
                if(!password.getText().toString().equals(confirmPassword.getText().toString()))
                {
                    password.setError("Passwords do not match");
                    return;
                }

                if(email.getText().toString().isEmpty())
                {
                    email.setError("Email is required");
                    return;
                }

                if(password.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty())
                {
                    password.setError("Password is required");
                    confirmPassword.setError("Confirm Password is required");
                    return;
                }

                if(password.getText().toString().length() < 6)
                {
                    password.setError("Password has to be at least 6 characters");
                    return;
                }

                if(firstName.getText().toString().isEmpty())
                {
                    firstName.setError("Please enter a first name");
                    return;
                }

                if(lastName.getText().toString().isEmpty())
                {
                    lastName.setError("Please enter a last name");
                    return;
                }

                if(dateOfBirth.getText().toString().isEmpty())
                {
                    dateOfBirth.setError("Please enter a date of birth");
                    return;
                }

                if(gender.getText().toString().isEmpty())
                {
                    gender.setError("Please enter your gender");
                    return;
                }

                //Creates the user in our firebase database
                fAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(activity_registerPeer.this, "Signed In!", Toast.LENGTH_SHORT).show();

                        //Temporarily signs the new user into the Firebase database so we can extract the UID
                        fAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            //Runs if the username and password match
                            public void onSuccess(AuthResult authResult) {

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            //Runs if the username and password don't match
                            public void onFailure(@NonNull @org.jetbrains.annotations.NotNull Exception e) {

                            }
                        });

                        //Extracts the UID
                        FirebaseUser currentUser = Objects.requireNonNull(fAuth.getCurrentUser());

                        //Adds the user's data to our firebase realtime database
                        userData newUser = new userData(email.getText().toString(), firstName.getText().toString(), lastName.getText().toString(), dateOfBirth.getText().toString(), gender.getText().toString());
                        users.child(currentUser.getUid()).setValue(newUser);

                        //Signs the user out for security
                        fAuth.signOut();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(activity_registerPeer.this, "Something went wrong :(", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
            }
        });
    }
}