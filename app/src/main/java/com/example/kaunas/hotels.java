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

import java.util.List;

public class hotels extends AppCompatActivity {

    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((toolbar));
        getSupportActionBar().setTitle(null);

        ImageView back = (ImageView) findViewById(R.id.back);

        recycler = findViewById(R.id.recycler_view);


        //------DUOMENŲ BAZĖ

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database").allowMainThreadQueries().build();

        DB_hotels hotel1 = new DB_hotels("HOF Hotel", "Maironio g. 21A", 4);
        DB_hotels hotel2 = new DB_hotels("Kaunas Garden", "Laisvės alėja 38e", 5);
        DB_hotels hotel3 = new DB_hotels("Magnus Hotel", "Vytauto pr. 25, 44352", 4);
        DB_hotels hotel4 = new DB_hotels("Happy Inn", "Vytauto pr. 21", 2);
        DB_hotels hotel5 = new DB_hotels("Kaunas GUEST HOUSE", "Rotušės a. 21", 5);

        db.DB_hotelsDao().insertAll(hotel1,hotel2, hotel3, hotel4,hotel5);

        List<DB_hotels> hotelsList = db.DB_hotelsDao().getAll();

        //------DUOMENŲ BAZĖ

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addView(recycler, 0);

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