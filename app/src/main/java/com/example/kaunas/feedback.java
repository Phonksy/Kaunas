package com.example.kaunas;

import androidx.annotation.NonNull;
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
    private DatabaseReference mDatabase;
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
}