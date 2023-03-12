package com.example.kaunas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class bestRestaurants extends AppCompatActivity {

    ImageView back3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_restaurants);

        back3 = findViewById(R.id.back3);

        back3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bestRestaurants.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}