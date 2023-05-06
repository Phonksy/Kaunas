package com.example.kaunas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class feedback extends AppCompatActivity {

    EditText vardas, atsiliepimas;
    Button palikti, perziureti;
    TextView pav;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((toolbar));
        getSupportActionBar().setTitle(null);
        pav = findViewById(R.id.pavad);
        pav.setText("ATSILIEPIMAI");

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(feedback.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //DUOMBAZĖ

        mDatabase = FirebaseDatabase.getInstance().getReference();

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

                if (vardasTXT.length() < 1)
                    Toast.makeText(feedback.this, "Įveskite vardą", Toast.LENGTH_LONG).show();
                else if (atsiliepimasTXT.length() < 1)
                    Toast.makeText(feedback.this, "Įveskite komentarą", Toast.LENGTH_LONG).show();
                else {
                    paliktiAtsiliepima(vardasTXT, atsiliepimasTXT);
                    vardas.setText("");
                    atsiliepimas.setText("");
                }
            }
        });

    }

    public void paliktiAtsiliepima(String vardas, String komentaras) {
        atsiliepimas ats = new atsiliepimas(vardas, komentaras);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String dt = sdf.format(new Date());

        FirebaseDatabase.getInstance().getReference("Atsiliepimai").child(dt).setValue(ats).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(feedback.this, "Saved", Toast.LENGTH_SHORT).show();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(feedback.this, e.getMessage().toString(),  Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(feedback.this, aboutKaunas.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_food) {
            Intent intent2 = new Intent(feedback.this, bestRestaurants.class);
            startActivity(intent2);
            return true;
        } else if (id == R.id.nav_places) {
            Intent intent3 = new Intent(feedback.this, placesToVisit.class);
            startActivity(intent3);
            return true;
        } else if (id == R.id.nav_hotels) {
            Intent intent5 = new Intent(feedback.this, hotels.class);
            startActivity(intent5);
            return true;
        } else if (id == R.id.nav_news) {
            gotoUrl("https://kauno.diena.lt");
            return true;
        } else if (id == R.id.nav_feedback) {
            Intent intent6 = new Intent(feedback.this, feedback.class);
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