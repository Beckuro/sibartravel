package com.travel.sibar.sibartravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

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

    }

    public String toString(String[] arr){

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            sb.append(arr[i] + " ");
        }
        return sb.toString();
    }
}
