package com.example.kaunas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.List;

public class hotels extends AppCompatActivity {

    String []data = {"Hotel1", "hotel2", "hotel3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((toolbar));
        getSupportActionBar().setTitle(null);

        ImageView back = (ImageView) findViewById(R.id.back);

        // DUOMENŲ BAZĖ

        List<String> items = new LinkedList<>();
        items.add("Code it");

        RecyclerView recyclerView = findViewById(R.id.duom);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        hotelsAdapter adapter = new hotelsAdapter(items);
        recyclerView.setAdapter(adapter);


        // DUOMENŲ BAZĖ


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hotels.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item)
    {
        switch(item.getItemId()) {
            case
                    R.id.nav_about:
                Intent intent = new Intent(hotels.this, aboutKaunas.class);
                startActivity(intent);
                break;
            case
                    R.id.nav_food:
                Intent intent2 = new Intent(hotels.this, bestRestaurants.class);
                startActivity(intent2);
                break;
            case
                    R.id.nav_places:
                Intent intent3 = new Intent(hotels.this, placesToVisit.class);
                startActivity(intent3);
                break;
            case
                    R.id.nav_quiz:
                Intent intent4 = new Intent(hotels.this, quiz.class);
                startActivity(intent4);
                break;
            case
                    R.id.nav_news:
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