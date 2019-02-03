package com.rds.andjaradji.bootcampmap.Fragments;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rds.andjaradji.bootcampmap.Model.BootcampLocation;
import com.rds.andjaradji.bootcampmap.R;
import com.rds.andjaradji.bootcampmap.Services.DataService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private MarkerOptions userMarker;
    private LocationListFragment locListFragment;


    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locListFragment = (LocationListFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.bootcampLocLisContainerID);

        if (locListFragment == null){
            locListFragment = LocationListFragment.newInstance();
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.bootcampLocLisContainerID, locListFragment)
                    .commit();
        }

        final EditText postalInput = view.findViewById(R.id.postalCodeInputID);
        postalInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction()== KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    String input = postalInput.getText().toString();
                    int postalCode = Integer.parseInt(input);

                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(postalInput.getWindowToken(),0);
                    showList();

                }

                return false;
            }
        });
        hideList();
        return view;
    }

    private void hideList() {
        getActivity().getSupportFragmentManager().beginTransaction().hide(locListFragment).commit();
    }

    private void showList() {
        getActivity().getSupportFragmentManager().beginTransaction().show(locListFragment).commit();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    public void setUserMarker (LatLng latLng){
        if (userMarker == null) {
            userMarker = new MarkerOptions().position(latLng).title("Current Location");
            mMap.addMarker(userMarker);
            Log.d("UYAKUYA", "Current Location Lat: " + latLng.latitude + " - Long: " + latLng.longitude);
        }

        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address>addressList = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
            int zipCode = Integer.parseInt(addressList.get(0).getPostalCode());
            updateMapPostal(zipCode);
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateMapPostal(92284);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,8));
    }

    private void updateMapPostal(int postalCode) {
        ArrayList<BootcampLocation> bootcamps = DataService.getInstance().getBootcampNearBy(postalCode);

        for (int x = 0; x<bootcamps.size(); x++){
            BootcampLocation bootcamp = bootcamps.get(x);
            MarkerOptions bootcampMarker = new MarkerOptions().position(new LatLng(bootcamp.getBootcampLatitude(), bootcamp.getBootcampLongitude()));
            bootcampMarker.title(bootcamp.getBootcampLocationTitle());
            bootcampMarker.snippet(bootcamp.getBootcampLocationAddress());
            bootcampMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_location));
            mMap.addMarker(bootcampMarker);

        }
    }

}
