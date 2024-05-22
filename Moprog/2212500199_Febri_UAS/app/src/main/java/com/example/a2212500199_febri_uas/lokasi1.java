package com.example.a2212500199_febri_uas;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class lokasi1 extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Marker mCurrLocationMarker;
    private Location mLastLocation;
    public static final int REQUEST_LOCATION_CODE = 99;
    int PROXIMITY_RADIUS = 5000;
    double latitude,longitude;
    Button normal, hybrid, satelit, terrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi1);

        normal  = findViewById(R.id.normal);
        hybrid  = findViewById(R.id.hybrid);
        satelit = findViewById(R.id.satelit);
        terrain = findViewById(R.id.terrain);

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        hybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        satelit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });

        terrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });


        // Memuat SupportMapFragment dan memberi notifikasi saat telah siap.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        LatLng n = new LatLng(-6.2120207,106.4697075);
        mMap.addMarker(new MarkerOptions().position(n).title("Gerai Cendrawasih"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(n));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(n,10));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomOut());

        LatLng ta = new LatLng(-6.1941232,106.4898348);
        mMap.addMarker(new MarkerOptions().position(ta).title("Tomo Books"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ta));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ta,10));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomOut());

        LatLng ng = new LatLng(-6.1802996,106.5165067);
        mMap.addMarker(new MarkerOptions().position(ng).title("Toko Buku Kita Baca"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ta,10));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomOut());

        LatLng e = new LatLng(-6.133559719070491, 106.67823234718105);
        mMap.addMarker(new MarkerOptions().position(e).title("Ruang Edu Kahfi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(e));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e,10));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomOut());

        LatLng ra = new LatLng(-6.3268147206716545, 106.86903991534608);
        mMap.addMarker(new MarkerOptions().position(ra).title("Toko Buku Kiky"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ra));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ra,10));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomOut());

        LatLng g = new LatLng(-6.194406528393705, 106.6930360327416);
        mMap.addMarker(new MarkerOptions().position(g).title("Toko Buku An - Najm"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(g));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(g,10));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomOut());

        LatLng jo = new LatLng(-6.224328258529438, 106.84436876643876);
        mMap.addMarker(new MarkerOptions().position(jo).title("Lafsya Book Store"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(jo));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jo,10));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomOut());

        LatLng ga = new LatLng(-6.081200015473503, 106.75304378837743);
        mMap.addMarker(new MarkerOptions().position(ga).title("D'mawar Books"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ga));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ga,10));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomOut());

        LatLng ge = new LatLng(-6.2475036654375, 106.84306364703265);
        mMap.addMarker(new MarkerOptions().position(ge).title("Book Store Anas"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ge));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ge,10));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomOut());

        LatLng gm = new LatLng(-6.410743335306143, 106.80202491333246);
        mMap.addMarker(new MarkerOptions().position(gm).title("Toko Buku Akhyar"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gm));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gm,10));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomOut());

        LatLng gc = new LatLng(-6.281530540423323, 106.80780446444977);
        mMap.addMarker(new MarkerOptions().position(gc).title("Buku Cerita Anak"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gc));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gc,10));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomOut());

        LatLng mm = new LatLng(-6.2344375269809085, 106.86488584910525);
        mMap.addMarker(new MarkerOptions().position(mm).title("Sinar Mika"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mm));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mm,10));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomOut());

        LatLng km = new LatLng(-6.203813677940138, 106.86695495095819);
        mMap.addMarker(new MarkerOptions().position(km).title("Asyam Books"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(km));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(km,10));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomOut());

        LatLng cc = new LatLng(-6.176879307525756, 106.734742153419);
        mMap.addMarker(new MarkerOptions().position(cc).title("Buku kita"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cc));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cc,10));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomOut());

        LatLng m = new LatLng(-6.214357838555071, 106.83199547136842);
        mMap.addMarker(new MarkerOptions().position(m).title("Sarang Buku"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m,10));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(m));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomOut());



        //Memulai Google Play Services
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }


    }

    private void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    private String getUrl(double latitude , double longitude , String nearbyPlace)
    {

        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+latitude+","+longitude);
        googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);
        googlePlaceUrl.append("&type="+nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key="+"AIzaSyDhzWavNomVWm0O5Lra-908Uww-o-t6DBA");

        Log.d("lokasi1", "url = "+googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latLng.latitude, latLng.longitude)).zoom(16).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //menghentikan pembaruan lokasi
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // Izin diberikan.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Izin ditolak.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }


}