package com.apps.frontendsiswa.fitursiswa.rekomendasi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.apps.frontendsiswa.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LoadRekomendasi extends AppCompatActivity {
    private String KEY_NAMELATITUDE = "AMBILLATITUDE";
    private String KEY_NAMELONGITUDE = "AMBILLONGITUDE";

    String kirimLatitude;
    String kirimLongitude;
    String alamat;
    private Boolean oke = false;
    List<Address> addressList= null;
    String okok = "aku aid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_rekomendasi);

        Geocoder geocoder = new Geocoder(LoadRekomendasi.this, Locale.getDefault());
        try {

            addressList = geocoder.getFromLocation(-6.928122919052839, 109.11778153851628, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Toasty.info(LoadRekomendasi.this, addressList.get(0).getAddressLine(0), Toasty.LENGTH_SHORT).show();

        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts
                                .RequestMultiplePermissions(), result -> {
                            Boolean fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                            Boolean coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION,false);
                            if (fineLocationGranted != null && fineLocationGranted) {
                                // Precise location access granted.
                            } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                // Only approximate location access granted.
                            } else {
                                // No location access granted.
                            }
                        }
                );

        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });



        // GET CURRENT LOCATION
        FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            List<Address> addressList= null;
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Geocoder geocoder = new Geocoder(LoadRekomendasi.this, Locale.getDefault());

                    try {
                        addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        if (addressList != null){
                            Address returnAdd = addressList.get(0);
                            StringBuilder stringBuilder = new StringBuilder("");
                            for (int i=0; i<returnAdd.getMaxAddressLineIndex(); i++){
                                stringBuilder.append(returnAdd.getAddressLine(i)).append("\n");
                            }
                            Log.w("My location Address", stringBuilder.toString());
                        }else {
                            Log.w("My Location Address", "no address");
                        }
                    } catch (IOException e){
                        e.printStackTrace();
                    }


                    // Do it all with location
                    Log.e("My Current location", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
                    Log.d("My Current location", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
                    // Display in Toast
                    Toast.makeText(LoadRekomendasi.this,
                            "Lat : " + location.getLatitude() + " Long : " + location.getLongitude() +addressList.get(0).getAddressLine(0),
                            Toast.LENGTH_LONG).show();
                    double dooo= location.getLatitude();
                    kirimLatitude = String.valueOf(location.getLatitude());
                    kirimLongitude = String.valueOf(location.getLongitude());
                }
            }
        });


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent i = new Intent(LoadRekomendasi.this, FragmentActivity.class);
                i.putExtra(KEY_NAMELATITUDE, kirimLatitude);
                i.putExtra(KEY_NAMELONGITUDE, kirimLongitude);
                startActivity(i);
                //startActivity(new Intent(getApplicationContext(), RekomendasiActivity.class));
                finish();
            }
        }, 5000L); //3000 L = 5 detik



    }



}