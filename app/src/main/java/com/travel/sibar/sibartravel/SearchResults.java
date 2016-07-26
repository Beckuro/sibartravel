package com.travel.sibar.sibartravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchResults extends AppCompatActivity {

    ListView lv;
    ArrayList<String> nameArrayList;
    ArrayList<String> imgURLArrayList;
    ArrayList<String> distanceArrayList;
    ArrayList<String> coordinatesArrayList;
    ArrayList<String> priceArrayList;
    ArrayList<String> placeIDArrayList;

    SearchResultsModel srModel = new SearchResultsModel();
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        db = new DatabaseHelper(getApplicationContext());

        Intent intent = getIntent();

        String coordinates = intent.getExtras().getString("coordinates");
        String actQuery = intent.getExtras().getString("activities");

        srModel = db.getSearchResults(coordinates, actQuery);

        lv = (ListView) findViewById(R.id.lvResult);

        nameArrayList = srModel.getName();
        imgURLArrayList = srModel.getImgURL();
        distanceArrayList = srModel.getDistance();
        priceArrayList = srModel.getPrice();
        placeIDArrayList = srModel.getPlaceID();
        coordinatesArrayList = srModel.getCoordinates();

        String[] imgURL = imgURLArrayList.toArray(new String[imgURLArrayList.size()]);
        String[] name = nameArrayList.toArray(new String[nameArrayList.size()]);
        String[] price = priceArrayList.toArray(new String[priceArrayList.size()]);
        String[] coordinatesArr = coordinatesArrayList.toArray(new String[coordinatesArrayList.size()]);
        String[] distance = distanceArrayList.toArray(new String[distanceArrayList.size()]);

        SearchResultsAdapter adapter = new SearchResultsAdapter(this, imgURL, name, price, coordinatesArr, distance);

        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), placeIDArrayList.get(position)+ " " + nameArrayList.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchResults.this, PlaceDetail.class);

                intent.putExtra("placeID", placeIDArrayList.get(position));
                intent.putExtra("coordinates", coordinatesArrayList.get(position));
                intent.putExtra("imgURL", imgURLArrayList.get(position));
                intent.putExtra("name", nameArrayList.get(position));
                startActivity(intent);
            }
        });


        ImageView iv = (ImageView) findViewById(R.id.background1);

        String url = "http://winsource.com/wp-content/uploads/2013/06/now-mountain.png";
        Picasso.with(getApplicationContext()).load(url).fit().into(iv);

        lv.bringToFront();


    }

    public String toString(String[] arr){

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            sb.append(arr[i] + " ");
        }
        return sb.toString();
    }
}