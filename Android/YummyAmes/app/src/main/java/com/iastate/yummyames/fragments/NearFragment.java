package com.iastate.yummyames.fragments;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;
import com.iastate.yummyames.R;
import com.iastate.yummyames.objects.Restaurant;
import com.iastate.yummyames.singletons.RestaurantSingleton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.iastate.yummyames.R.id.container;


public class NearFragment extends Fragment implements OnMapReadyCallback{

    GoogleMap mMap;
    SupportMapFragment mapFragment;
    private ArrayList<Restaurant> restaurants;
    private Button button;

    public NearFragment() { }

//    public static NearFragment newInstance(ArrayList<Restaurant> restaurants)
//    {
//        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList("RESTAURANTS", restaurants);
//        NearFragment nearFragment = new NearFragment();
//        nearFragment.setArguments(bundle);
//        return nearFragment;
//    }
//    private void readBundle(Bundle bundle)
//    {
//        if(bundle != null){
//            restaurants = bundle.getParcelableArrayList("RESTAURANTS");
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v =  inflater.inflate(R.layout.fragment_near, container, false);

        button = (Button) v.findViewById(R.id.button4);
        EditText editText = (EditText) getActivity().findViewById(R.id.editText);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if(mapFragment == null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onSearch(v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        restaurants = RestaurantSingleton.getInstance().getRestaurants();
        return v;
    }


    private void onSearch(View view) throws IOException
    {
        EditText editText = (EditText) getActivity().findViewById(R.id.editText);
        String lt = editText.getText().toString();
        List<Address> addressList = null;
        if(lt != null || !lt.equals("") )
        {
            LatLng latLng = getLng(lt);
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
        }
    }
    private LatLng getLng(String mAddress)
    {
        List<Address> addressList = null;
        LatLng latLng = null;
        if(mAddress != null || !mAddress.equals("") ) {

            Geocoder geocoder = new Geocoder(getContext());
            try {
                addressList = geocoder.getFromLocationName(mAddress, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = addressList.get(0);
            latLng = new LatLng(address.getLatitude(), address.getLongitude());
        }
        return latLng;
    }


    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        LatLng current = new LatLng(42.028136, -93.649571);
        mMap.addMarker(new MarkerOptions().position(current).title("You are here"));


        for(int i=0; i<restaurants.size(); i++){
            LatLng placesLng = getLng(restaurants.get(i).getAddress());
            double distance = SphericalUtil.computeDistanceBetween(current, getLng(restaurants.get(i).getAddress()));
            mMap.addMarker(new MarkerOptions().position(placesLng).title("Distance from" + restaurants.get(i).getName() + "is" + distance + "m"));
        }

        LatLng initLng = getLng("Ames");
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initLng,13));
    }
}
