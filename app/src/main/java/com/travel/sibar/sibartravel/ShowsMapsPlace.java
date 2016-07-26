package com.travel.sibar.sibartravel;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class ShowsMapsPlace extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String coordinates;
    String placesname;
    private static final int WIDTH_MAX = 50;

    private static final int HUE_MAX = 360;

    private static final int ALPHA_MAX = 255;

    private Polyline mMutablePolyline;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows_maps_place);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

//    public static ArrayList getDirections(double lat1, double lon1, double lat2, double lon2) {
//        String url = "http://maps.googleapis.com/maps/api/directions/xml?origin=" +lat1 + "," + lon1  + "&destination=" + lat2 + "," + lon2 + "&sensor=false&units=metric";
//        String tag[] = { "lat", "lng" };
//        ArrayList list_of_geopoints = new ArrayList();
//        HttpResponse response = null;
//        try {
//            HttpClient httpClient = new DefaultHttpClient();
//            HttpContext localContext = new BasicHttpContext();
//            HttpPost httpPost = new HttpPost(url);
//            response = httpClient.execute(httpPost, localContext);
//            InputStream in = response.getEntity().getContent();
//            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            Document doc = builder.parse(in);
//            if (doc != null) {
//                NodeList nl1, nl2;
//                nl1 = doc.getElementsByTagName(tag[0]);
//                nl2 = doc.getElementsByTagName(tag[1]);
//                if (nl1.getLength() > 0) {
//                    list_of_geopoints = new ArrayList();
//                    for (int i = 0; i < nl1.getLength(); i++) {
//                        Node node1 = nl1.item(i);
//                        Node node2 = nl2.item(i);
//                        double lat = Double.parseDouble(node1.getTextContent());
//                        double lng = Double.parseDouble(node2.getTextContent());
//                        list_of_geopoints.add(new Barcode.GeoPoint((int) (lat * 1E6), (int) (lng * 1E6)));
//                    }
//                } else {
//                    // No points found
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list_of_geopoints;
//    }


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
        GMapV2Direction md = new GMapV2Direction();

        MapsService location = new MapsService(this);
        final Intent intent = getIntent();
        String[] places;
        placesname = intent.getExtras().getString("name");
        double lat = 0;
        double lon = 0;
        coordinates = intent.getExtras().getString("coordinates");
        if(coordinates!= null && coordinates.length() > 0){
            places = coordinates.split(",");
            lat = Double.parseDouble(places[0]);
            lon = Double.parseDouble(places[1]);

        }


        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng dest = new LatLng(location.getLatitude(),location.getLongitude());
        LatLng curr = new LatLng(lat, lon);
        Document doc = md.getDocument(dest,curr,GMapV2Direction.MODE_DRIVING);
        ArrayList<LatLng> directionPoint = md.getDirection(doc);
        PolylineOptions rectLine = new PolylineOptions().width(6).color(
                Color.RED);

        for (int i = 0; i < directionPoint.size(); i++) {
            rectLine.add(directionPoint.get(i));
        }
        Polyline polyline = mMap.addPolyline(rectLine);


//        Polyline polyline = mMap.addPolyline(new PolylineOptions().add(dest,curr).width(5).color(Color.BLUE));

        mMap.addMarker(new MarkerOptions().position(curr).title(placesname));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(curr));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curr, 12.0f));
    }
}
