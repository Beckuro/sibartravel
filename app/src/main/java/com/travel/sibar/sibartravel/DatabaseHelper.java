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

    public static String DB_NAME = "aspera";

    public static final int DB_VERSION = 1;

    public static final String TB_DESTINATION = "Destinations";
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
        return tempDB != null ? true : false;
    }

    /***
     * Copy database from source code assets to device
     * @throws IOException
     */
    public void copyDataBase() throws IOException {
        try {
            InputStream myInput = context.getAssets().open(DB_NAME);
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

        if (!true) {

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

        try {
            c = db.rawQuery("SELECT * FROM " + TB_DESTINATION, null);
            if (c == null) return null;

            String name;
            c.moveToFirst();
            do {
                name = c.getString(1);
                listUsers.add(name);
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.e("tle99", e.getMessage());
        }

        db.close();

        return listUsers;
    }

    public List< String > getAllActivities(String id) {
        List < String > listActivities = new ArrayList<String >();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;

        try {

            c = db.rawQuery("SELECT * FROM Activities WHERE idPlace=" + id, null);
            if ( c == null ) return null;

            c.moveToFirst();

            while(c != null) {
                String actID = c.getString(c.getColumnIndex("actID"));
                String idPlace = c.getString(c.getColumnIndex("idPlace"));
                String desc = c.getString(c.getColumnIndex("description"));
                String idIcon = c.getString(c.getColumnIndex("idIcon"));
                String coordinates = c.getString(c.getColumnIndex("coordiantes"));

                listActivities.add(actID + "," + idPlace + "," + desc + "," + idIcon + "," + coordinates);

                c.moveToNext();
            }

        } catch (Exception e) {
            Log.e("tle9999999999", e.getMessage());
        }

        db.close();
        return listActivities;
    }
}
