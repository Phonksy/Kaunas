package com.example.kaunas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class aboutKaunas extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_kaunas);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((toolbar));
        getSupportActionBar().setTitle(null);

        ImageView back = (ImageView) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(aboutKaunas.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_food:
                Intent intent2 = new Intent(aboutKaunas.this, bestRestaurants.class);
                startActivity(intent2);
                break;
            case R.id.nav_places:
                Intent intent3 = new Intent(aboutKaunas.this, placesToVisit.class);
                startActivity(intent3);
                break;
            case R.id.nav_quiz:
                Intent intent4 = new Intent(aboutKaunas.this, quiz.class);
                startActivity(intent4);
                break;
            case R.id.nav_hotels:
                Intent intent5 = new Intent(aboutKaunas.this, hotels.class);
                startActivity(intent5);
                break;
            case R.id.nav_news:
                gotoUrl("https://kauno.diena.lt");
                break;

            default:
                break;
        }
        return true;
    }


    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}