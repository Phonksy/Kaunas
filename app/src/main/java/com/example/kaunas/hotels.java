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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class hotels extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter adapter;
    ArrayList<Object> duom;
    DatabaseReference database;
    TextView pav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((toolbar));
        getSupportActionBar().setTitle(null);
        pav = findViewById(R.id.pavad);
        pav.setText("VIEŠBUČIAI");

        ImageView back = (ImageView) findViewById(R.id.back);

        recyclerView = findViewById(R.id.recyclerview);
        database = FirebaseDatabase.getInstance().getReference("Viešbučiai");
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