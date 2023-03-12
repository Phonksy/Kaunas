package com.example.kaunas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button newspaper;
    Button about;
    Button placestovisit;

    Button restaurants;
    Button quiz;
    Button hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newspaper = findViewById(R.id.news);
        about = findViewById(R.id.about);
        placestovisit = findViewById(R.id.placestovisit);
        restaurants = findViewById(R.id.restaurants);
        quiz = findViewById(R.id.quiz);
        hotels = findViewById(R.id.hotels);

        newspaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://kauno.diena.lt");
            }
        });

        about.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, aboutKaunas.class);
                startActivity(intent);
            }
        });

        placestovisit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, placesToVisit.class);
                startActivity(intent);
            }
        });

        restaurants.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, bestRestaurants.class);
                startActivity(intent);
            }
        });

        quiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, quiz.class);
                startActivity(intent);
            }
        });
        hotels.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, hotels.class);
                startActivity(intent);
            }
        });

    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}