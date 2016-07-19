package com.travel.sibar.sibartravel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MapsActivity extends AppCompatActivity {
    MapsService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        service = new MapsService(MapsActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        TextView text = (TextView) findViewById(R.id.textView);

        if(service.canGetLocation) {
            text.setText(service.getLatitude() + " , " + service.getLongitude());
        } else {
            service.showSettingAlert();
        }

    }
}
