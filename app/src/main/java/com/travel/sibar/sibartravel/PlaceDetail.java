package com.travel.sibar.sibartravel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class PlaceDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        
        Intent intent = getIntent();

        ListView lv = (ListView) findViewById(R.id.lvActDetail);

        String coordinates = intent.getExtras().getString("coordinates");
        String imgURL = intent.getExtras().getString("imgURL");
        String placeID = intent.getExtras().getString("placeID");
        String name = intent.getExtras().getString("name");

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        PlaceDetailModel pdm = db.getPlaceDetail(placeID);
        String description = db.getPlaceDescription(placeID);

        if(description != null){
            Log.d("Description", description);
        } else {
            Log.d("Description", "null");
        }

        String[] actDescription = pdm.getDescription().toArray(new String[pdm.getDescription().size()]);
        String[] iconList = pdm.getIcon().toArray(new String[pdm.getIcon().size()]);

        PlaceDetailAdapter adapter = new PlaceDetailAdapter(this, actDescription, iconList);

        Log.d("Size Desc", actDescription.length+"");
        Log.d("Size icon", iconList.length+"");

        lv.setAdapter(adapter);

        ImageView ivm = (ImageView) findViewById(R.id.imgPlaceDetail);
        TextView tv = (TextView) findViewById(R.id.txtPlaceDetail);
        TextView desc = (TextView) findViewById(R.id.txtDescriptionDetail);

        desc.setText(description);
        desc.bringToFront();

        tv.setText(name);
        Picasso.with(getApplicationContext()).load(imgURL).fit().into(ivm);

        ImageView iv = (ImageView) findViewById(R.id.background2);

        String url = "http://winsource.com/wp-content/uploads/2013/06/now-mountain.png";
        Picasso.with(getApplicationContext()).load(url).fit().into(iv);

        lv.bringToFront();
        ivm.bringToFront();
        tv.bringToFront();

        //Toast.makeText(getApplicationContext(), placeID, Toast.LENGTH_SHORT).show();
    }
}
