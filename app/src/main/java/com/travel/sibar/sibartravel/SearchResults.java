package com.travel.sibar.sibartravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class SearchResults extends AppCompatActivity {

    ListView lv;
    String[] name;
    String[] imgURL;
    String[] distance;
    String[] lat_rad;
    String[] long_rad;
    String[] price;
    String[] placeID;

    SearchResultsModel srModel = new SearchResultsModel();
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        db = new DatabaseHelper(getApplicationContext());

        Intent intent = getIntent();

        String coordinates = intent.getExtras().getString("coordinates");
        String catQuery = intent.getExtras().getString("categories");

        srModel = db.getSearchResults(coordinates, catQuery);

        lv = (ListView) findViewById(R.id.lvResult);

        name = srModel.getName();
        imgURL = srModel.getImgURL();
        distance = srModel.getDistance();
        price = srModel.getPrice();
        placeID = srModel.getPlaceID();
        lat_rad = srModel.getLat_rad();
        long_rad = srModel.getLong_rad();

        SearchResultsAdapter adapter = new SearchResultsAdapter(this, imgURL, name, price, lat_rad, long_rad, distance);

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
