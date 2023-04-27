package com.example.kaunas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class hotels extends AppCompatActivity {
    RecyclerView recyclerView;
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
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Hotels").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        List<Hotel> viesbuciai;
        db.hotelDao().nukeTable();

        Hotel viesbutis1 = new Hotel("HOF hotel", "Maironio g. 21A", "4 žvaigždutės", "https://www.hofhotel.eu");
        Hotel viesbutis2 = new Hotel("Kaunas Garden", "Laisvės al. 38E", "3 žvaigždutės", "https://www.kaunasgarden.lt");
        Hotel viesbutis3 = new Hotel("Guest house", "Rotušės a. 21", "5 žvaigždutės", "http://kaunas.lcn.lt/sveciunamai/en/");
        Hotel viesbutis4 = new Hotel("Happy Inn", "Vytauto pr. 21", "4 žvaigždutės", "https://www.facebook.com/trumpalaikenuoma/");
        Hotel viesbutis5 = new Hotel("Moxy Kaunas Center", "Maironio g. 19", "4.5 žvaigždutės", "https://www.facebook.com/Moxykaunascenter/");
        Hotel viesbutis6 = new Hotel("Very Bad Hotel", "Žemaičių g. 144", "4.5 žvaigždutės", "https://verybadhotel.lt");
        Hotel viesbutis7 = new Hotel("Kaunas City", "Laisvės al. 90", "4 žvaigždutės", "https://kaunascityhotel.com/en/");
        Hotel viesbutis8 = new Hotel("Radisson Hotel", "K. Donelaičio g. 27", "4 žvaigždutės", "https://www.radissonhotels.com/en-us/hotels/radisson-kaunas");

        db.hotelDao().insertAll(viesbutis1);
        db.hotelDao().insertAll(viesbutis2);
        db.hotelDao().insertAll(viesbutis3);
        db.hotelDao().insertAll(viesbutis4);
        db.hotelDao().insertAll(viesbutis5);
        db.hotelDao().insertAll(viesbutis6);
        db.hotelDao().insertAll(viesbutis7);
        db.hotelDao().insertAll(viesbutis8);

        viesbuciai = db.hotelDao().getAllHotels();

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, viesbuciai);
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
        } else if (itemId == R.id.nav_feedback) {
            Intent intent6 = new Intent(hotels.this, feedback.class);
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