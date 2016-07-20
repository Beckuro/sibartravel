package com.travel.sibar.sibartravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class MapsActivity extends AppCompatActivity {
    MapsService service;
    double lat ;
    double lon ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        service = new MapsService(MapsActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        EditText text = (EditText) findViewById(R.id.editText);

        if(service.canGetLocation) {
            setLocation();
            text.setText(lat + " , " + lon);
        } else {
            service.showSettingAlert();
        }

        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEvent.ACTION_UP == event.getAction()) {
                    Intent intent = new Intent(MapsActivity.this, ShowsMaps.class);
                    startActivity(intent);
                }
                return true;
            }
        });

    }

    public void setLocation(){

            lat = service.getLatitude();
            lon = service.getLongitude();
    }

    public double getLat(){
        return this.lat;
    }

    public double getLon(){
        return this.lon;
    }
}
