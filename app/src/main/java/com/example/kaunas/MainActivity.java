package com.example.kaunas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.room.Room;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button newspaper;
    Button about;
    Button placestovisit;
    Button restaurants;
    Button quiz;
    Button hotels;

    Button feedback;
    TextView pav;

    private final String url = "https://api.openweathermap.org/data/2.5/weather?q=Kaunas&appid=754bd930c832caf89b0b549909da0b45";
    private static final DecimalFormat df = new DecimalFormat("0.0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newspaper = findViewById(R.id.news);
        about = findViewById(R.id.about);
        placestovisit = findViewById(R.id.placestovisit);
        restaurants = findViewById(R.id.restaurants);
        quiz = findViewById(R.id.quiz);
        hotels = findViewById(R.id.hotels);
        feedback = findViewById(R.id.feedback);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((toolbar));
        getSupportActionBar().setTitle(null);
        getSupportActionBar().hide();
        pav = findViewById(R.id.pavad);
        pav.setText("");

        System.out.println(R.drawable.moxy_kaunas_center);
        System.out.println(R.drawable.radisson_hotel);
        System.out.println(R.drawable.very_bad_hotel);

        Date data = new Date( );
        SimpleDateFormat ft =
                new SimpleDateFormat ("yyyy-MM-dd \n HH:mm");

        TextView laikas = findViewById(R.id.laikas);
        laikas.setText(ft.format(data));
        TextView oras = findViewById(R.id.oras);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.d("response", response);

                String output = "";
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                    double temperature = jsonObjectMain.getDouble("temp") - 273.15;
                    output += "Dabar Kaune yra: " + df.format(temperature) + " °C";
                    oras.setText(output);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "KLAIDA", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);



        newspaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://kauno.diena.lt");
            }
        });

        about.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, aboutKaunas.class);
                startActivity(intent);
            }
        });

        placestovisit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, placesToVisit.class);
                startActivity(intent);
            }
        });

        restaurants.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, bestRestaurants.class);
                startActivity(intent);
            }
        });

        quiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, quiz.class);
                startActivity(intent);
            }
        });
        hotels.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, hotels.class);
                startActivity(intent);
            }
        });

        feedback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, feedback.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_about) {
            Intent intent = new Intent(MainActivity.this, aboutKaunas.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.nav_food) {
            Intent intent2 = new Intent(MainActivity.this, bestRestaurants.class);
            startActivity(intent2);
            return true;
        } else if (itemId == R.id.nav_places) {
            Intent intent3 = new Intent(MainActivity.this, placesToVisit.class);
            startActivity(intent3);
            return true;
        } else if (itemId == R.id.nav_quiz) {
            Intent intent4 = new Intent(MainActivity.this, quiz.class);
            startActivity(intent4);
            return true;
        } else if (itemId == R.id.nav_news) {
            gotoUrl("https://kauno.diena.lt");
            return true;
        } else if (itemId == R.id.nav_feedback) {
            Intent intent6 = new Intent(MainActivity.this, feedback.class);
            startActivity(intent6);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this )
                .setTitle("Ar tikrai norite uždaryti programėlę?")
                .setPositiveButton("Ne", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Taip", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finishAffinity();
                        System.exit(0);
                    }
                })
                .setCancelable(false)
                .show();
    }
}
