package pl.ppm.gitfitscrub;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.location.LocationManager;
import android.location.Location;
import android.content.Context;
import android.location.Criteria;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.CameraPosition;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_maps );
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager ()
                .findFragmentById ( R.id.map );
        mapFragment.getMapAsync ( this );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // wysokość i szerokość geograficzna Nowego Sącza
        final double posY = 49.617400;
        final double posX = 20.714878;
        mMap = googleMap;
        LatLng ns = new LatLng ( posY, posX );
        mMap.addMarker ( new MarkerOptions ()
                .icon ( BitmapDescriptorFactory.defaultMarker ( BitmapDescriptorFactory.HUE_CYAN ) )
                .position ( new LatLng ( posY, posX ) )
                .title ( "Nowy Sącz" ) );
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ns));
        mMap.animateCamera ( CameraUpdateFactory.zoomTo ( 12 ) );
        if (ActivityCompat.checkSelfPermission ( this, Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission ( this, Manifest.permission.ACCESS_COARSE_LOCATION ) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled ( true );
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
            if (location == null) {
                criteria.setAccuracy(Criteria.ACCURACY_COARSE);
                String provider = locationManager.getBestProvider(criteria, true);
                location = locationManager.getLastKnownLocation(provider);
            }
            if (location != null) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(location.getLatitude(), location.getLongitude())).zoom(12).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        }
    }
}
