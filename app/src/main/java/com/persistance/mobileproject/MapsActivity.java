package com.persistance.mobileproject;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.persistance.mobileproject.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng southwestBound = new LatLng(21.4207, -17.0667); // Southwest corner of Morocco
        LatLng northeastBound = new LatLng(35.7917, -1.4423);

        LatLngBounds bounds = new LatLngBounds(southwestBound, northeastBound);
        LatLng center = bounds.getCenter();

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50)); // '50' is the padding

        mMap.addMarker(new MarkerOptions().position(center).title("Center of the Map"));
    }

}