package com.example.kaunas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class hotels extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> Pavadinimai;
    ArrayList<String> Adresai;
    ArrayList<String> Vertinimai;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((toolbar));
        getSupportActionBar().setTitle(null);

        ImageView back = (ImageView) findViewById(R.id.back);

        // DUOMENYS

        Pavadinimai = new ArrayList<>();
        Pavadinimai.add("HOF hotel");
        Pavadinimai.add("Kaunas Garden");
        Pavadinimai.add("Guest house");
        Pavadinimai.add("Happy inn");
        Adresai = new ArrayList<>();
        Adresai.add("Maironio g. 21A");
        Adresai.add("Laisvės alėja 38e");
        Adresai.add("Rotušės a. 21");
        Adresai.add("Vytauto pr. 21");
        Vertinimai = new ArrayList<>();
        Vertinimai.add("4 žvaigždutės");
        Vertinimai.add("3 žvaigždutės");
        Vertinimai.add("5 žvaigždutės");
        Vertinimai.add("4 žvaigždutės");

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, Pavadinimai, Adresai, Vertinimai);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // DUOMENYS


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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_about) {
            Intent intent = new Intent(hotels.this, aboutKaunas.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.nav_food) {
            Intent intent2 = new Intent(hotels.this, bestRestaurants.class);
            startActivity(intent2);
            return true;
        } else if (itemId == R.id.nav_places) {
            Intent intent3 = new Intent(hotels.this, placesToVisit.class);
            startActivity(intent3);
            return true;
        } else if (itemId == R.id.nav_quiz) {
            Intent intent4 = new Intent(hotels.this, quiz.class);
            startActivity(intent4);
            return true;
        } else if (itemId == R.id.nav_news) {
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