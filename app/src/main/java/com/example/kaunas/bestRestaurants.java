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

import java.util.ArrayList;
import java.util.List;

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

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Restaurants").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        List<Hotel> restoranai;
        db.hotelDao().nukeTable();

        Hotel restoranas1 = new Hotel("Mamma Pizza", "Vytauto pr. 37", "4.5 žvaigždutės", "https://www.mammapizza.lt", R.drawable.mamma_pizza, "https://goo.gl/maps/6KSu83RjuyLr3X8Q8");
        Hotel restoranas2 = new Hotel("Jurgis ir Drakonas", "Kurpių g. 26", "4.5 žvaigždutės", "https://jurgisirdrakonas.lt", R.drawable.jurgis_ir_drakonas, "https://goo.gl/maps/9BMjSFQvGNtCfSE87");
        Hotel restoranas3 = new Hotel("Manami", "Islandijos pl. 32 PLC Mega", "4.5 žvaigždutės", "https://www.manami.lt", R.drawable.manami, "https://goo.gl/maps/8P4W1AB93UbF73Vm6");
        Hotel restoranas4 = new Hotel("Ali Šokoladinė", "Laisvės al. 41", "5 žvaigždutės", "https://alisokoladine.lt/lt/", R.drawable.ali_sokoladine, "https://goo.gl/maps/6vuLFLDJoaksY2wg8");
        Hotel restoranas5 = new Hotel("Talutti - Fresh and Tasty", "Taikos pr. 12", "4.5 žvaigždutės", "https://talutti.lt", R.drawable.talutti, "https://goo.gl/maps/mzJp5x56Et6QVdzK8");
        Hotel restoranas6 = new Hotel("Casa della Pasta", "Laisvės al. 27", "4.5 žvaigždutės", "https://www.casadellapasta.lt", R.drawable.casa_della_pasta, "https://goo.gl/maps/pmdE4prHLegtxrtL6");
        Hotel restoranas7 = new Hotel("Bernelių užeiga", "M. Valančiaus g. 9", "4 žvaigždutės", "https://berneliuuzeiga.lt", R.drawable.berneliu_uzeiga, "https://goo.gl/maps/dzmetFe8ybroazP4A");
        Hotel restoranas8 = new Hotel("Pelėdinė", "Šv. Gertrūdos g. 22", "4.5 žvaigždutės", "https://www.peledine.lt", R.drawable.peledine, "https://goo.gl/maps/uCwTNxSRdRX8TH2X6");

        db.hotelDao().insertAll(restoranas1);
        db.hotelDao().insertAll(restoranas2);
        db.hotelDao().insertAll(restoranas3);
        db.hotelDao().insertAll(restoranas4);
        db.hotelDao().insertAll(restoranas5);
        db.hotelDao().insertAll(restoranas6);
        db.hotelDao().insertAll(restoranas7);
        db.hotelDao().insertAll(restoranas8);

        restoranai = db.hotelDao().getAllHotels();

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, restoranai);
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
        } else if (id == R.id.nav_feedback) {
            Intent intent6 = new Intent(this, feedback.class);
            startActivity(intent6);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}