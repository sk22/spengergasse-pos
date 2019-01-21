package at.spengergasse.kai17521.lab06map;

import android.location.Location;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.IMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.INTERNET;
import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {
  private MapView map = null;
  private MyLocationNewOverlay mLocationOverlay = null;
  private IMyLocationProvider locationProvider;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Configuration.getInstance().load(
      this,
      PreferenceManager.getDefaultSharedPreferences(this)
    );

    setContentView(R.layout.activity_main);

    requestPermissions();

    map = findViewById(R.id.map);
    map.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
    map.getController().zoomTo(2d);
    map.getController().setCenter(new GeoPoint(0.0, 0.0));
    map.setMultiTouchControls(true);
    map.setTilesScaledToDpi(true);

    this.locationProvider = new GpsMyLocationProvider(getApplicationContext());
    this.mLocationOverlay = new MyLocationNewOverlay(locationProvider, map);
    map.getOverlays().add(this.mLocationOverlay);

    // TODO:
    // - Request permissions for own position
    // - Add menu to choose categories
    // - Add POIs (points of interest) to map
    // - Add overlay for POI details (display name and address)
  }

  private void requestPermissions() {
    final int permissionAccessFineLocation = ContextCompat
      .checkSelfPermission(this, ACCESS_FINE_LOCATION);
    final int permissionAccessNetworkState = ContextCompat
      .checkSelfPermission(this, ACCESS_NETWORK_STATE);
    final int permissionInternet = ContextCompat
      .checkSelfPermission(this, INTERNET);

    if (permissionAccessFineLocation != PERMISSION_GRANTED ||
        permissionAccessNetworkState != PERMISSION_GRANTED ||
        permissionInternet != PERMISSION_GRANTED) {
      int PERMISSIONS_LOCATION = 1;
      ActivityCompat.requestPermissions(this, new String[] {
        ACCESS_FINE_LOCATION,
        ACCESS_NETWORK_STATE,
        INTERNET
      }, PERMISSIONS_LOCATION);
    }
  }

  public void onClickLocate(View view) {
    this.mLocationOverlay.enableMyLocation();
    if (locationProvider.getLastKnownLocation() != null) {
      animateTo(locationProvider.getLastKnownLocation());
    }
  }

  private void animateTo(Location location) {
    map.getController().animateTo(
      new GeoPoint(location),
      17.0, null
    );
  }
}
