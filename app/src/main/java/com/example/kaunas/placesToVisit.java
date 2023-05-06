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

public class placesToVisit extends AppCompatActivity implements OnMapReadyCallback, LocationListener {


    private GoogleMap googleMap;
    private MapView mapView;

    private LocationManager locationManager;
    private Location currentLocation;
    TextView pav;

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

        // Get location updates
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            // Get the last known location from the location provider
            currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            // Move the camera to the last known location
            if (currentLocation != null) {
                LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                mapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                    }
                });
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        if (locationManager != null) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        }
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
    public boolean onCreateOptionsMenu (Menu menu)
    {
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

        // Add markers for historic landmarks in Kaunas
        Marker kaunasCastleMarker = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.8953, 23.8863))
                .title("Kaunas Castle")
                .snippet("Medieval castle in Kaunas Old Town.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        Marker stMichaelArchangelMarker = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.8959, 23.8901))
                .title("St. Michael the Archangel Church")
                .snippet("Historic church in Kaunas Old Town.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        Marker kaunasCathedralMarker = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.8989, 23.8982))
                .title("Kaunas Cathedral Basilica")
                .snippet("Catholic cathedral in Kaunas Old Town.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        Marker houseOfPerkunasMarker = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.8977, 23.8907))
                .title("House of Perkūnas")
                .snippet("Historic building in Kaunas Old Town.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        Marker vytautasWarMuseumMarker = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.8972, 23.8908))
                .title("Vytautas the Great War Museum")
                .snippet("Military museum in Kaunas Old Town.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        Marker ninthFortMarker = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.8971, 23.9011))
                .title("Ninth Fort Museum")
                .snippet("Museum and memorial to the victims of Nazi and Soviet terror.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        Marker pazaislisMonasteryMarker = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.9343, 23.9463))
                .title("Pazaislis Monastery")
                .snippet("Baroque monastery on the outskirts of Kaunas.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        Marker kaunasTownHallMarker = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.8982, 23.9044))
                .title("Kaunas Town Hall")
                .snippet("Historic town hall in Kaunas Old Town.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        Marker kaunasGhettoMarker = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.9044, 23.8938))
                .title("Kaunas Ghetto")
                .snippet("Site of the wartime Jewish ghetto in Kaunas.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        Marker christsResurrectionMarker = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.9009, 23.8935))
                .title("Christ's Resurrection Church")
                .snippet("Orthodox church in Kaunas Old Town.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        // Enable the zoom controls on the map
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        // Check if location permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Enable the location layer on the map
            googleMap.setMyLocationEnabled(true);

            // Move the camera to the user's current location
            if (currentLocation != null) {
                LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5));
            }
            else {
                LatLng kaunasLatLng = new LatLng(54.8985, 23.9036);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kaunasLatLng, 5));
            }
        } else {
            // Request location permission if not granted
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        // Update the current location
        //currentLocation = location;
    }
}