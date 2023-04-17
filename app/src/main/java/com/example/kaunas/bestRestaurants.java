package com.example.kaunas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class bestRestaurants extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> Pavadinimai;
    ArrayList<String> Adresai;
    ArrayList<String> Vertinimai;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_restaurants);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((toolbar));
        getSupportActionBar().setTitle(null);

        ImageView back = (ImageView) findViewById(R.id.back);

        // DUOMENYS

        Pavadinimai = new ArrayList<>();
        Pavadinimai.add("Mamma Pizza");
        Pavadinimai.add("Jurgis ir Drakonas");
        Pavadinimai.add("Manami");
        Pavadinimai.add("Katpėdėlė");
        Pavadinimai.add("Restoranas DIA");
        Pavadinimai.add("Agave");
        Pavadinimai.add("Restoranas Siesta");
        Adresai = new ArrayList<>();
        Adresai.add("Vytauto pr. 37");
        Adresai.add("Kurpių g. 26");
        Adresai.add("Islandijos pl. 32, PLC Mega");
        Adresai.add("V. Krėvės pr. 57");
        Adresai.add("Maironio g. 9");
        Adresai.add("Rotušės a. 3");
        Adresai.add("Pakrantės g. 4, Vareikonys, Kauno r.");
        Vertinimai = new ArrayList<>();
        Vertinimai.add("4.5 žvaigždutės");
        Vertinimai.add("4.5 žvaigždutės");
        Vertinimai.add("4.5 žvaigždutės");
        Vertinimai.add("5 žvaigždutės");
        Vertinimai.add("4.5 žvaigždutės");
        Vertinimai.add("4.5 žvaigždutės");
        Vertinimai.add("4.5 žvaigždutės");

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, Pavadinimai);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // DUOMENYS


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bestRestaurants.this, MainActivity.class);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            Intent intent2 = new Intent(this, aboutKaunas.class);
            startActivity(intent2);
            return true;
        } else if (id == R.id.nav_places) {
            Intent intent3 = new Intent(this, placesToVisit.class);
            startActivity(intent3);
            return true;
        } else if (id == R.id.nav_quiz) {
            Intent intent4 = new Intent(this, quiz.class);
            startActivity(intent4);
            return true;
        } else if (id == R.id.nav_hotels) {
            Intent intent5 = new Intent(this, hotels.class);
            startActivity(intent5);
            return true;
        } else if (id == R.id.nav_news) {
            gotoUrl("https://kauno.diena.lt");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}