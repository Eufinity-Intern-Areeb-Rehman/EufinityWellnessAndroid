//Eufinity Wellness
//Script written by Areeb Rehman on 7/26/21
//Usage: This is meant to redirect the user to the register page depending on if they're a counselor or peer

package com.eufinitywellness.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.eufinitywellness.android.CustomElements.EufinityThemeButton;

public class activity_register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setNavigationBarColor(Color.BLACK);

        //Declares the buttons we need to go on the next pages
        EufinityThemeButton peerButton = findViewById(R.id.peerButton);
        EufinityThemeButton counselorButton = findViewById(R.id.counselorButton);

        //Detects if the user tapped the peer button
        peerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), activity_registerPeer.class);
                startActivity(i);
            }
        });
    }
}