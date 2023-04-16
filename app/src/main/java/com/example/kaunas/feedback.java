package com.example.kaunas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class feedback extends AppCompatActivity {

    EditText vardas, atsiliepimas;
    Button palikti, perziureti;

    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((toolbar));
        getSupportActionBar().setTitle(null);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(feedback.this, MainActivity.class);
                startActivity(intent);
            }
        });

        DB = new DBHelper(this);

        vardas=findViewById(R.id.vardas);
        atsiliepimas=findViewById(R.id.atsiliepimas);
        palikti=findViewById(R.id.pateikti);
        perziureti=findViewById(R.id.perziureti);

        perziureti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(feedback.this, feedbackList.class));
            }
        });

        palikti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vardasTXT = vardas.getText().toString();
                String atsiliepimasTXT = atsiliepimas.getText().toString();

                Boolean checkinsertData = DB.insertFeedback(vardasTXT, atsiliepimasTXT);
                if (checkinsertData)
                {
                    Toast.makeText(feedback.this, "PRIDĖTA", Toast.LENGTH_SHORT).show();
                    vardas.setText("");
                    atsiliepimas.setText("");
                }

                else Toast.makeText(feedback.this, "NEPRIDĖTA", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}