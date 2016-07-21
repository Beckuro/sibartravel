package com.travel.sibar.sibartravel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class DetailPlaceActivity extends AppCompatActivity {

    private ParallaxScollListView mListView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);

        mListView = (ParallaxScollListView) findViewById(R.id.layout_listview);
        View header = LayoutInflater.from(this).inflate(R.layout.listview_header, null);
        mImageView = (ImageView) header.findViewById(R.id.layout_header_image);

        mListView.setZoomRatio(ParallaxScollListView.ZOOM_X2);
        mListView.setParallaxImageView(mImageView);
        mListView.addHeaderView(header);

        String desc[] = {"a", "b", "c"};
        int[] icon = {R.drawable.civixmap, R.drawable.refresh48, R.drawable.search48};
        int iconLoc = R.drawable.user48;

        CustomAdapter adapter = new CustomAdapter(this, desc, icon, iconLoc);
        mListView.setAdapter(adapter);
    }
}
