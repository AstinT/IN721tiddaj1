package bit.tiddaj1.googleapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity
{
    SupportMapFragment mapFragment;
    GoogleMap mMap;
    LatLng DunedinLatLong;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DunedinLatLong = new LatLng(-45.87, 170.50);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(new MapCallBackClass());
    }

    public class MapCallBackClass implements OnMapReadyCallback
    {
        @Override
        public void onMapReady(GoogleMap googleMap)
        {
            mMap = googleMap;

            mMap.addMarker(new MarkerOptions().position(DunedinLatLong).title("Dunedin"));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(DunedinLatLong));
        }
    }
}
