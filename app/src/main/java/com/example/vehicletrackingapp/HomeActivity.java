package com.example.vehicletrackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {
    Button track, adddebtor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        track= (Button)findViewById(R.id.btntrack);
        adddebtor=(Button)findViewById(R.id.btnAdddebtor);

       // track.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(),TrackingActivity.class);
               // startActivity(intent);
           // }
       // });

        adddebtor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddDebtorActivity.class);
                startActivity(intent);

            }
        });
    }
}