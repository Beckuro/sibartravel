package com.travel.sibar.sibartravel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.io.IOException;
import java.util.List;

public class DetailPlaceActivity extends AppCompatActivity {

    private ParallaxScollListView mListView;
    private ImageView mImageView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);

        dbHelper = new DatabaseHelper(getApplicationContext());

        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mListView = (ParallaxScollListView) findViewById(R.id.layout_listview);
        View header = LayoutInflater.from(this).inflate(R.layout.listview_header, null);
        mImageView = (ImageView) header.findViewById(R.id.layout_header_image);
        mImageView.setImageResource(R.drawable.merapi);

        mListView.setZoomRatio(ParallaxScollListView.ZOOM_X2);
        mListView.setParallaxImageView(mImageView);
        mListView.addHeaderView(header);

        List<String> activities = dbHelper.getAllActivities("1");

        String[] desc = new String[activities.size()];
        int[] icon = new int [activities.size()];
        int iconLoc = R.drawable.user48;

        for (int i = 0; i < activities.size(); i++) {
            String[] input = activities.get(i).split(",");
            desc[i] = input[2];
            int resID = getResources().getIdentifier(input[3] , "drawable", getPackageName());
            icon[i]= resID;
        }

        CustomAdapter adapter = new CustomAdapter(this, desc, icon, iconLoc);
        mListView.setAdapter(adapter);
    }
}
