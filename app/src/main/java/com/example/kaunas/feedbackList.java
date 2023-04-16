package com.example.kaunas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
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
}