package com.example.kaunas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class feedbackList extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> name, feedback;
    DBHelper DB;
    FeedbackAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((toolbar));
        getSupportActionBar().setTitle(null);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(feedbackList.this, MainActivity.class);
                startActivity(intent);
            }
        });

        DB = new DBHelper(this);
        name = new ArrayList<>();
        feedback=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerview);
        adapter=new FeedbackAdapter(this, name,feedback);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();

    }

    private void displayData()
    {
        Cursor cursor = DB.getData();
        if (cursor.getCount()==0)
        {
            Toast.makeText(this, "No Entry Exists", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                name.add(cursor.getString(0));
                feedback.add(cursor.getString(1));
            }
        }
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
            Intent intent = new Intent(feedbackList.this, aboutKaunas.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_food) {
            Intent intent2 = new Intent(feedbackList.this, bestRestaurants.class);
            startActivity(intent2);
            return true;
        } else if (id == R.id.nav_places) {
            Intent intent3 = new Intent(feedbackList.this, placesToVisit.class);
            startActivity(intent3);
            return true;
        } else if (id == R.id.nav_hotels) {
            Intent intent5 = new Intent(feedbackList.this, hotels.class);
            startActivity(intent5);
            return true;
        } else if (id == R.id.nav_news) {
            gotoUrl("https://kauno.diena.lt");
            return true;
        } else if (id == R.id.nav_feedback) {
            Intent intent6 = new Intent(feedbackList.this, feedback.class);
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