/**
 * Created by Adrian - 15002426 on 2017/08/28.
 * OPSC Assignment 1
 * Task is do create a gps tracker,and also make use of the google map api to display locations
 * references used
 * https://www.youtube.com/watch?v=dr0zEmuDuIk
 */
package adrianbeukes.opsc7312_ass1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.lang.String;

public class Question1A extends AppCompatActivity {

    //declarations
    TextView locationText;
    Button but;
    Geocoder geo;
    List<Address> address;
    LocationManager locMan;
    LocationListener loclist;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        //gets ids from layout values
        locationText = (TextView) findViewById(R.id.txtLocation);
        but = (Button) findViewById(R.id.btnGetLoc);
        geo = new Geocoder(this, Locale.getDefault());
        locMan = (LocationManager) getSystemService(LOCATION_SERVICE);

    loclist = new LocationListener()
    {
        @Override
        public void onLocationChanged(Location location)
        {
            try
            {
                //gets current location and assign it to the text view
                address = geo.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                locationText.setText( "Location is "+ location.getLongitude() + " " + location.getLatitude());
                locationText.setText(address.get(0).getAddressLine(0));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        //default methods generated
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle)
        {
        }
        @Override
        public void onProviderEnabled(String s)
        {
        }
        //starts activity
        @Override
        public void onProviderDisabled(String s)
        {
            Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(i);
        }
    };
    configure_button();
    }

    //permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }

    void configure_button()
    {
        // permission checker
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }
        // runs when permission granted
        but.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //noinspection MissingPermission
                locMan.requestLocationUpdates("gps", 1000, 0, loclist);
            }
        });
    }
}
