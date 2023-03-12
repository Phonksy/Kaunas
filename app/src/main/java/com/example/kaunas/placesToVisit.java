package com.example.kaunas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class placesToVisit extends AppCompatActivity {

    ImageView back2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_to_visit);

        back2 = findViewById(R.id.back2);

        back2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(placesToVisit.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}