package com.example.kaunas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class hotels extends AppCompatActivity {

    ImageView back5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        back5 = findViewById(R.id.back5);

        back5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hotels.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}