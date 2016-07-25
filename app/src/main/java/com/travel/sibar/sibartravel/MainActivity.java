package com.travel.sibar.sibartravel;

import android.content.Intent;
<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
=======
>>>>>>> Map
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHeplper;
    ListView lvUsers;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        startActivity(new Intent(MainActivity.this, SearchResults.class));
=======
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
>>>>>>> Map

        /** Below is example usage of retrieving database, comment if unnesecassry *//*

       /* Log.d("Status", "Start dbHelper");
        dbHeplper = new DatabaseHelper(getApplicationContext());
        try {
            dbHeplper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("Status", "Start ListVIew");

        lvUsers = (ListView)findViewById(R.id.listView);
        List<String> listUsers = dbHeplper.getAllUsers();

<<<<<<< HEAD
=======

        if(listUsers != null){
            Log.d("Status", "ListView != null");

            for(int i = 0; i < listUsers.size(); i++){
                Log.d("Isi LV", listUsers.get(i));

            }
            adapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_list_item_1, android.R.id.text1,
                    listUsers);
            lvUsers.setAdapter(adapter);
        }

>>>>>>> Map
        Log.d("Status", "Finish");*/
    }
}
