package com.travel.sibar.sibartravel;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ibrahim on 18/07/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    /*
    * Taken from http://icetea09.com/blog/2014/01/22/android-use-existing-sqlite-database-in-android-app/
     */

    public static String DB_PATH = "/data/data/com.travel.sibar.sibartravel/databases/";

    public static String DB_NAME = "Aspera.sqlite";

    public static final int DB_VERSION = 1;

    public static final String TB_DESTINATION = "Destination";
    public static final String TB_ACTIVITIES = "Activities";

    private SQLiteDatabase myDB;
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @
            Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @
            Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    @
            Override
    public synchronized void close() {
        if (myDB != null) {
            myDB.close();
        }
        super.close();
    }

    /***
     * Check if the database is exist on device or not
     * @return
     */
    private boolean checkDataBase() {
        SQLiteDatabase tempDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            tempDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
            Log.e("tle99 - check", e.getMessage());
        }
        if (tempDB != null)
            tempDB.close();
        return false;
        //return tempDB != null ? true : false;
    }

    /***
     * Copy database from source code assets to device
     * @throws IOException
     */
    public void copyDataBase() throws IOException {
        try {

            InputStream myInput = context.getAssets().open(DB_NAME);

            Log.d("myInput to string",myInput.toString());

            String outputFileName = DB_PATH + DB_NAME;
            OutputStream myOutput = new FileOutputStream(outputFileName);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e) {
            Log.e("tle99 - copyDatabase", e.getMessage());
        }

    }

    /***
     * Open database
     * @throws SQLException
     */
    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    /***
     * Check if the database doesn't exist on device, create new one
     * @throws IOException
     */
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if (dbExist) {

        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e("tle99 - create", e.getMessage());
            }
        }
    }

    public List< String > getAllUsers() {
        List < String > listUsers = new ArrayList< String >();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;

        String categoriesQuery = "mountain";
        try {
            String query = "Select * FROM " + TB_DESTINATION + " WHERE " + "categories" + " =  \"" + categoriesQuery + "\"";
            c = db.rawQuery(query, null);

            if (c == null) return null;

            String name;
            String placeID;
            String accomodation;
            String description;
            String mainPictUrl;
            String categories;
            String priceLevel;
            String long_rad;
            String lat_rad;

            int nameIndex = c.getColumnIndex("name");
            int placeIDIndex = c.getColumnIndex("placeID");
            int accomodationIndex = c.getColumnIndex("accomodation");
            int descriptionIndex = c.getColumnIndex("description");
            int mainPictUrlIndex = c.getColumnIndex("mainPictUrl");
            int categoriesIndex = c.getColumnIndex("categories");
            int priceLevelIndex = c.getColumnIndex("priceLevel");
            int long_radIndex = c.getColumnIndex("long_rad");
            int lat_radIndex = c.getColumnIndex("lat_rad");

            c.moveToFirst();

            int counter = 1;
            while(c != null){

                name = c.getString(nameIndex);
                placeID = c.getString(placeIDIndex);
                accomodation = c.getString(accomodationIndex);
                description = c.getString(descriptionIndex);
                mainPictUrl = c.getString(mainPictUrlIndex);
                categories = c.getString(categoriesIndex);
                priceLevel = c.getString(priceLevelIndex);
                long_rad = c.getString(long_radIndex);
                lat_rad = c.getString(lat_radIndex);

                StringBuilder sb = new StringBuilder();
                sb.append(name + " " + placeID + " " + accomodation + " " + description + " " + mainPictUrl + " " + categories + " " + priceLevel + " " + long_rad + " " + lat_rad);


                Log.d("Hasil "+ counter, sb.toString());


                counter++;
                c.moveToNext();
            }


            c.close();
        } catch (Exception e) {
            Log.e("tle99", e.getMessage());
        }

        db.close();

        return listUsers;
    }

    public SearchResultsModel getSearchResults(String coordinates, String actQuery){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        SearchResultsModel searchResultsModel = new SearchResultsModel();

        String[] arrCoordinates = coordinates.split(",");

        double lat_deg_query = Double.parseDouble(arrCoordinates[0]);
        double long_deg_query = Double.parseDouble(arrCoordinates[1]);

        try {
            String query = "Select *  FROM " + TB_DESTINATION + " WHERE " + actQuery + " =  \"" + "TRUE" + "\"";
            c = db.rawQuery(query, null);

            if (c == null) return null;

            String name;
            String placeID;
            String mainPictUrl;
            String priceLevel;
            String coord;

            int nameIndex = c.getColumnIndex("name");
            int placeIDIndex = c.getColumnIndex("placeID");
            int mainPictUrlIndex = c.getColumnIndex("mainPictUrl");
            int priceLevelIndex = c.getColumnIndex("priceLevel");
            int coordinates_index = c.getColumnIndex("coordinates");

            c.moveToFirst();

            int counter = 0;
            while(c != null){

                name = c.getString(nameIndex);
                placeID = c.getString(placeIDIndex);
                mainPictUrl = c.getString(mainPictUrlIndex);
                priceLevel = c.getString(priceLevelIndex);
                coord = c.getString(coordinates_index);

                ArrayList<String> tempName = searchResultsModel.getName();
                tempName.add(name.toUpperCase());
                searchResultsModel.setName(tempName);

                ArrayList<String> tempPlaceID = searchResultsModel.getPlaceID();
                tempPlaceID.add(placeID);
                searchResultsModel.setPlaceID(tempPlaceID);

                ArrayList<String> tempURL = searchResultsModel.getImgURL();
                tempURL.add(mainPictUrl);
                searchResultsModel.setImgURL(tempURL);

                ArrayList<String> tempPrice = searchResultsModel.getPrice();
                priceLevel = getPriceLevel(Integer.parseInt(priceLevel)).toUpperCase();
                tempPrice.add(priceLevel);
                searchResultsModel.setPrice(tempPrice);

                ArrayList<String> tempCoordinates = searchResultsModel.getCoordinates();
                tempCoordinates.add(coord);
                searchResultsModel.setCoordinates(tempCoordinates);

                ArrayList<String> tempDistance = searchResultsModel.getDistance();

                String[] place_coordinates = coord.split(",");

                double place_latitude = Double.parseDouble(place_coordinates[0]);
                double place_longitude = Double.parseDouble(place_coordinates[1]);

                double distance = getDistance(lat_deg_query, long_deg_query, place_latitude, place_longitude, 'K');
                distance = Math.ceil(distance);
                String strDistance = distance + " KM AWAY".toUpperCase();

                tempDistance.add(strDistance);

                searchResultsModel.setDistance(tempDistance);

                counter++;
                c.moveToNext();
            }


            c.close();
        } catch (Exception e) {
            Log.e("tle99", e.getMessage());
        }

        db.close();

        return searchResultsModel;
    }

    public String getPlaceDescription(String placeID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        String description = "";
        try {

            String query = "Select description from Destination WHERE " + "placeID" + " =  \"" + placeID + "\"";
            c = db.rawQuery(query, null);

            if(c == null) return null;



            int descIndex = c.getColumnIndex("description");

            c.moveToFirst();

            while(c != null){

                description = c.getString(descIndex);

                c.moveToNext();
            }

            return description;

        } catch (Exception e){
            e.printStackTrace();
        }

        return description;
    }
    public PlaceDetailModel getPlaceDetail(String placeID){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        PlaceDetailModel pdm = new PlaceDetailModel();

        try {

            String query = "Select *  FROM " + TB_ACTIVITIES + " WHERE " + "idPlace" + " =  \"" + placeID + "\"";

            c = db.rawQuery(query, null);

            if (c == null) return null;

            String description;
            String iconName;

            int descIndex = c.getColumnIndex("description");
            int iconIndex = c.getColumnIndex("idIcon");

            c.moveToFirst();

            while(c!=null){

                description = c.getString(descIndex);
                iconName = c.getString(iconIndex);

                ArrayList<String> tempDesc = pdm.getDescription();
                tempDesc.add(description);
                pdm.setDescription(tempDesc);

                ArrayList<String> tempIconName = pdm.getIcon();
                tempIconName.add(iconName);
                pdm.setIcon(tempIconName);



                c.moveToNext();
            }



        } catch (Exception e){
            e.printStackTrace();
        }

        return pdm;
    }

    public String getPriceLevel(int n){
        if(n <= 1000000){
            return "Price Level - Cheap";
        } else if (n > 1000000 && n < 3000000){
            return "Price Level - Moderate";
        } else {
            return "Price Level - Expensive";
        }
    }

    private double getDistance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
