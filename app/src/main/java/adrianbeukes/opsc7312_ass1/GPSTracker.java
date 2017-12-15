/**
 * Created by Adrian - 15002426 on 2017/08/28.
 * OPSC Assignment 1
 * Task is do create a gps tracker,and also make use of the google map api to display locations
 * references used
 * https://www.youtube.com/watch?v=dr0zEmuDuIk
 */
package adrianbeukes.opsc7312_ass1;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;

import java.security.Provider;

import static android.content.Context.LOCATION_SERVICE;

public class GPSTracker extends Service implements LocationListener
{
    private final Context context;
    boolean isGPSEnabled =false;
    boolean isNetworkEnabled =false;
    boolean canGetLocation = false;

    Location location;
    protected LocationManager locationManager;

    public GPSTracker(Context context)
    {
        this.context=context;
    }

    //Create a GetLocation Method
    public  Location getLocation()
    {
        try
        {
            //sets up location manager
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
            isNetworkEnabled=locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER);

            //checks permission
            if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED )
            {
                if(isGPSEnabled)
                {
                    if(location==null)
                    {
                        //how frequnetly gps updates location
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000,10,this);
                        if(locationManager!=null)
                        {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                    }
                }
                // search for location from network instead of gps
                if(location==null)
                {
                    if(isNetworkEnabled)
                    {
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000,10,this);
                        if(locationManager!=null)
                        {
                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        }
                    }
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return  location;
    }

    // default method  implement LocList //
    public void onLocationChanged(Location location)
    {
    }
    public void onStatusChanged(String Provider, int status, Bundle extras)
    {
    }
    public void onProviderEnabled(String Provider)
    {
    }
    public void onProviderDisabled(String Provider)
    {
    }
    public IBinder onBind(Intent arg0)
    {
        return null;
    }
}