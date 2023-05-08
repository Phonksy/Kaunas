package com.example.kaunas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.MapStyleOptions;

import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;
import java.util.List;

public class placesToVisit extends AppCompatActivity implements OnMapReadyCallback, LocationListener {


    TextView pav;
    private GoogleMap googleMap;
    private MapView mapView;

    private List<Marker> markers = new ArrayList<Marker>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_to_visit);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((toolbar));
        getSupportActionBar().setTitle(null);
        pav = findViewById(R.id.pavad);
        pav.setText("LANKYTINOS VIETOS");

        ImageView back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(placesToVisit.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            Intent intent = new Intent(placesToVisit.this, aboutKaunas.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_food) {
            Intent intent2 = new Intent(placesToVisit.this, bestRestaurants.class);
            startActivity(intent2);
            return true;
        } else if (id == R.id.nav_quiz) {
            Intent intent4 = new Intent(placesToVisit.this, quiz.class);
            startActivity(intent4);
            return true;
        } else if (id == R.id.nav_hotels) {
            Intent intent5 = new Intent(placesToVisit.this, hotels.class);
            startActivity(intent5);
            return true;
        } else if (id == R.id.nav_news) {
            gotoUrl("https://kauno.diena.lt");
            return true;
        } else if (id == R.id.nav_feedback) {
            Intent intent6 = new Intent(placesToVisit.this, feedback.class);
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
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        Button pilys = findViewById(R.id.pilys);
        Button baznycios = findViewById(R.id.baznycios);
        Button muziejai = findViewById(R.id.muziejai);
        Button rodytiViska = findViewById(R.id.rodytiViska);


        Marker m1 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.8989, 23.8854))
                .title("Kauno pilis")
                .snippet("Viena seniausių Lietuvos mūrinių pilių, stovinti Kaune.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        m1.setTag("pilys");
        markers.add(m1);

        Marker m2 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.8969, 23.9213))
                .title("Kauno Šv. arkangelo Mykolo (Įgulos) bažnyčia")
                .snippet("Stovi Kauno naujamiestyje, rytinėje Laisvės alėjos dalyje.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        m2.setTag("baznycios");
        markers.add(m2);

        Marker m3 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.9009, 23.9795))
                .title("VI Kauno fortas")
                .snippet("Kauno tvirtovės dalis rytinėje miesto dalyje, Gričiupyje.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        m3.setTag("muziejai");
        markers.add(m3);

        Marker m4 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.8826, 23.9551))
                .title("Aukštųjų Šančių piliakalnis")
                .snippet("Piliakalnis Kauno savivaldybės teritorijoje, ant Nemuno kranto.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        m4.setTag("pilys");
        markers.add(m4);

        Marker m5 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.8999, 23.9121))
                .title("Vytauto Didžiojo karo muziejus")
                .snippet("Laikomas vertingu Kauno modernizmo architektūros pastatu.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        m5.setTag("muziejai");
        markers.add(m5);

        Marker m6 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.9454, 23.8709))
                .title("IX fortas")
                .snippet("Kauno tvirtovės dalis, išsidėsčiusi šiaurinėje miesto dalyje.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        m6.setTag("muziejai");
        markers.add(m6);

        Marker m7 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.8763, 24.0223))
                .title("Pažaislio vienuolynas")
                .snippet("Pastatų ansamblis Pažaislyje, Kauno marių šiaurės-vakariniame krante.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        m7.setTag("baznycios");
        markers.add(m7);

        Marker m8 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.8968, 23.8861))
                .title("Kauno rotušė")
                .snippet("Rotušė Kaune, Senamiestyje, netoli Nemuno ir Neries santakos.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        m8.setTag("baznycios");
        markers.add(m8);

        Marker m9 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.9158, 23.8883))
                .title("Kauno getas")
                .snippet("Nacistinės Vokietijos Kaune sukurtas getas, kuriame laikyti žydai.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
        m9.setTag("muziejai");
        markers.add(m9);

        Marker m10 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.9027, 23.9174))
                .title("Prisikėlimo bažnyčia")
                .snippet("Didžiausia monumentalios architektūros bazilikinė bažnyčia Baltijos šalyse.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        m10.setTag("baznycios");
        markers.add(m10);

        pilys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Marker m : markers) {
                    if (m.getTag()!="pilys")
                        m.setVisible(false);
                    else m.setVisible(true);
                }
            }
        });

        baznycios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Marker m : markers) {
                    if (m.getTag()!="baznycios")
                        m.setVisible(false);
                    else m.setVisible(true);
                }
            }
        });

        muziejai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Marker m : markers) {
                    if (m.getTag()!="muziejai")
                        m.setVisible(false);
                    else m.setVisible(true);
                }
            }
        });

        rodytiViska.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Marker m : markers) {
                    if (m.getTag()!="")
                        m.setVisible(true);
                }
            }
        });


        // Enable the zoom controls on the map
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        LatLng kaunasLatLng = new LatLng(54.8985, 23.9036);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kaunasLatLng, 11));
    }

    @Override
    public void onLocationChanged(Location location) {

    }
}