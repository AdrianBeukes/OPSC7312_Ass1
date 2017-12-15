/**
 * Created by Adrian - 15002426 on 2017/08/28.
 * OPSC Assignment 1
 * Task is do create a gps tracker,and also make use of the google map api to display locations
 * references used
 * https://www.youtube.com/watch?v=dr0zEmuDuIk
 */
package adrianbeukes.opsc7312_ass1;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{

    //declarations
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    /**
     * Manipulates the map once available.
     * marker placed at store location, durban
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
        @Override
        public void onMapReady(GoogleMap googleMap)
        {
            //marker location of the shop
            LatLng shop = new LatLng(-29.864293, 31.043675);
            googleMap.addMarker(new MarkerOptions().position(shop)
                    .title("Surfs Up"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(shop));
        }
}
