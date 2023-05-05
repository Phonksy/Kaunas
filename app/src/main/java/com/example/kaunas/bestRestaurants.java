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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class bestRestaurants extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    ArrayList<Object> duom;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_restaurants);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((toolbar));
        getSupportActionBar().setTitle(null);

        ImageView back = (ImageView) findViewById(R.id.back);

        recyclerView = findViewById(R.id.recyclerview);
        database = FirebaseDatabase.getInstance().getReference("Restoranai");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        duom = new ArrayList<>();
        adapter = new MyAdapter(this, duom);
        recyclerView.setAdapter(adapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Object obj = dataSnapshot.getValue(Object.class);
                    duom.add(obj);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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