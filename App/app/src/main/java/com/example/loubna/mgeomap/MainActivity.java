package com.example.loubna.mgeomap;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.geojson.GeoJsonFeature;
import com.google.maps.android.geojson.GeoJsonLayer;
import com.google.maps.android.geojson.GeoJsonPolygonStyle;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMyLocationButtonClickListener,GoogleMap.OnMyLocationClickListener {
    public GoogleMap gmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.g_map);
        mapFragment.getMapAsync(this);
    }

    @SuppressLint("MissingPermission")


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;

        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
*/

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            gmap.setMyLocationEnabled(true);

            gmap.setOnMyLocationButtonClickListener(this);
            gmap.setOnMyLocationClickListener(this);
        } else {
            Toast.makeText(this, R.string.error_permission_map, Toast.LENGTH_LONG).show();
        }


        LatLng center = new LatLng(35.7333061, -5.8229829);
        gmap.addMarker(new MarkerOptions().position(center).title("Marker in Tangier"));
        gmap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override public boolean onMarkerClick(Marker marker) {
                //  Take some action here
                return true;
            }

        });
        gmap.moveCamera(CameraUpdateFactory.newLatLng(center));
        try {

            GeoJsonLayer layer2 = new GeoJsonLayer(gmap, R.raw.natural, getApplicationContext());

            for (GeoJsonFeature feature : layer2.getFeatures()) {

                    GeoJsonPolygonStyle style = new GeoJsonPolygonStyle();
                    style.setFillColor(Color.rgb(127, 255, 0));
                    style.setStrokeWidth(4);
                    feature.setPolygonStyle(style);

                }




            layer2.addLayerToMap();


            layer2.setOnFeatureClickListener(new GeoJsonLayer.GeoJsonOnFeatureClickListener() {
                @Override
                public void onFeatureClick(GeoJsonFeature feature) {
                    StringBuffer buffer = new StringBuffer();

                    buffer.append("\n Name :" +feature.getProperty("name") + "\n");
                    buffer.append("\n Type :  " +feature.getProperty("type") + "\n");
                    buffer.append("\n Address :" +feature.getProperty("Adresse")+ "\n");
                    buffer.append("\n Longitude :  " +feature.getProperty("Longitude") + "\n");
                    buffer.append("\n Latitude :  " +feature.getProperty("Latitude") + "\n");

                            showMessage("Informations", buffer.toString());
                }
            });
  }

        catch(IOException e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Unable to read file", Toast.LENGTH_SHORT).show();
            } catch(JSONException e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Unable to parse file", Toast.LENGTH_SHORT).show();
            }

        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(center,10));
        gmap.getUiSettings().setZoomControlsEnabled(true);
//
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    //changing base map
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_options, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.normal_map:
                gmap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                gmap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                gmap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                gmap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
    }
    @Override
    public void onResume() { super.onResume();}

    public void geoLocate(View view) throws IOException {
        EditText et = (EditText) findViewById(R.id.editText3);
        String location = et.getText().toString();
        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(location,1);
        Address address = list.get(0);
        String locality = address.getLocality();
        Toast.makeText(this,locality,Toast.LENGTH_LONG).show();
        double lat =address.getLatitude();
        double lng =address.getLongitude();
        goToLocationZoom(lat,lng,15);
        //LatLng centrer = new LatLng(lat,lng);


    }

    private void goToLocationZoom(double lat,double lng,float zoom){
        LatLng ll =new LatLng(lat,lng);
        CameraUpdate update =CameraUpdateFactory.newLatLngZoom(ll,zoom);
        gmap.moveCamera(update);
        gmap.addMarker(new MarkerOptions().position(ll).title("Marker in Tangier"));

    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this,"My location button clicked ",Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location : \n"+location, Toast.LENGTH_LONG).show();
    }
}

