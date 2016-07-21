package com.travel.sibar.sibartravel;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SearchResults extends ListActivity {

    ListView lv;
    String[] name;
    String[] imgURL;
    String[] distance;
    String[] price;
    String[] placeID;

    SearchResultsModel srModel = new SearchResultsModel();
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        //lv = (ListView) findViewById(R.id.);

        db = new DatabaseHelper(getApplicationContext());

        srModel = db.getSearchResults();

        name = srModel.getName();
        imgURL = srModel.getImgURL();
        distance = srModel.getLong_rad();
        price = srModel.getPrice();
        placeID = srModel.getPlaceID();

        Log.d("Isi name", toString(name));
        Log.d("isi imgUrl", toString(imgURL));
        Log.d("isi distance", toString(distance));
        Log.d("isi price", toString(price));


        SearchResultsAdapter adapter = new SearchResultsAdapter(this, imgURL, name, price, distance, distance);

        Log.d("Count list adapter", adapter.getCount()+"");

        setListAdapter(adapter);

        ListAdapter list = getListAdapter();
        Log.d("Empty List", list.isEmpty()+"");
        Log.d("List content", list.toString());
        adapter.toString();

        Log.d("proses", "selesai");

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this, l.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
    }

    public String toString(String[] arr){

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            sb.append(arr[i] + " ");
        }
        return sb.toString();
    }
}
