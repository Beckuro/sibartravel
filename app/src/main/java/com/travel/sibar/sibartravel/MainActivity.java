package com.travel.sibar.sibartravel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHeplper;
    ListView lvUsers;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(MainActivity.this, SearchResults.class));

        /** Below is example usage of retrieving database, comment if unnesecassry *//*

        Log.d("Status", "Start dbHelper");
        dbHeplper = new DatabaseHelper(getApplicationContext());
        try {
            dbHeplper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("Status", "Start ListVIew");

        lvUsers = (ListView)findViewById(R.id.listView);
        List<String> listUsers = dbHeplper.getAllUsers();

        Log.d("Status", "Finish");*/
    }
}
