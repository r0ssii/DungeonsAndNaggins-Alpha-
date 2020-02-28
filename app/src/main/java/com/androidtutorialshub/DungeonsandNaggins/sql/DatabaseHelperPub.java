package com.androidtutorialshub.DungeonsandNaggins.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.androidtutorialshub.DungeonsandNaggins.model.Pub;


import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperPub extends SQLiteOpenHelper {

    //Database Version
    private static final int DATABASE_VERSION = 5;

    //Database Name
    private static final String DATABASE_NAME = "Pubmanager.db";

    private static final String TABLE_PUB = "pub";


    //Pub table names
    private static final String COLUMN_PUB_ID = "pub_id";
    private static final String COLUMN_PUB_NAME = "pub_name";
    private static final String COLUMN_PUB_LOCATION = "pub_location";
    private static final String COLUMN_PUB_GAMES = "pub_games";
    private static final String COLUMN_PUB_LAT = "pub_lat";
    private static final String COLUMN_PUB_LNG = "pub_lng";



    private String CREATE_PUB_TABLE = "CREATE TABLE " + TABLE_PUB + "("
            + COLUMN_PUB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PUB_NAME + " TEXT,"
            + COLUMN_PUB_LOCATION + " TEXT," + COLUMN_PUB_GAMES + " TEXT" + "," + COLUMN_PUB_LAT + " TEXT " + "," + COLUMN_PUB_LNG + " TEXT " + ")";

    // drop table sql query
    private String DROP_PUB_TABLE = "DROP TABLE IF EXISTS " + TABLE_PUB;

    public DatabaseHelperPub(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PUB_TABLE);
        db.execSQL("INSERT INTO pub (pub_id,pub_name,pub_location,pub_games,pub_lat,pub_lng) VALUES (1, 'Johnny Foxes', '123 Fake Street', 'Dungeons and Dragons','53.2217107', '-6.2191994')");
        db.execSQL("INSERT INTO pub (pub_id,pub_name,pub_location,pub_games,pub_lat,pub_lng) VALUES (2, 'The George', 'The George, Dublin 2', 'Magic The Gathering','53.343743', '-6.2646836')");
        db.execSQL("INSERT INTO pub (pub_id,pub_name,pub_location,pub_games,pub_lat,pub_lng) VALUES (3, 'Beer Keeper', 'Old Town Road, Dublin 16', 'Dungeons and Dragons','53.2947027', '-6.1416478')");
        db.execSQL("INSERT INTO pub (pub_id,pub_name,pub_location,pub_games,pub_lat,pub_lng) VALUES (4, 'The Lighthouse', 'The Lighthouse, Dublin 16', 'Magic The Gathering','53.2929888', '-6.1374504')");
        db.execSQL("INSERT INTO pub (pub_id,pub_name,pub_location,pub_games,pub_lat,pub_lng) VALUES (5, 'Bakers Corner', 'Bakers Corner, Monkstown, Dublin 18', 'Magic The Gathering','53.2805924', '-6.1577014')");
        db.execSQL("INSERT INTO pub (pub_id,pub_name,pub_location,pub_games,pub_lat,pub_lng) VALUES (6, 'IADT', 'IADT, Deans Grange, Dublin 18', 'Magic The Gathering', '53.2796486', '-6.1549324')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_PUB_TABLE);

        // Create tables again
        onCreate(db);
    }
    /**
     * Constructor
     *
     * @param
     */
    public void addPub(Pub pub) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PUB_NAME, pub.getName());
        values.put(COLUMN_PUB_LOCATION, pub.getLocation());
        values.put(COLUMN_PUB_GAMES, pub.getGames());
        values.put(COLUMN_PUB_LAT, pub.getLat());
        values.put(COLUMN_PUB_LNG, pub.getLng());

        // Inserting Row
        db.insert(TABLE_PUB, null, values);
        db.close();
    }


    public List<Pub> getAllPub() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_PUB_ID,
                COLUMN_PUB_NAME,
                COLUMN_PUB_LOCATION,
                COLUMN_PUB_GAMES,
                COLUMN_PUB_LAT,
                COLUMN_PUB_LNG

        };
        // sorting orders
        String sortOrder =
                COLUMN_PUB_NAME + " ASC";
        List<Pub> pubList = new ArrayList<Pub>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the pub table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT pub_id,pub_name;
         */
        Cursor cursor = db.query(TABLE_PUB, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pub pub = new Pub();
                pub.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PUB_ID))));
                pub.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PUB_NAME)));
                pub.setLocation(cursor.getString(cursor.getColumnIndex(COLUMN_PUB_LOCATION)));
                pub.setGames(cursor.getString(cursor.getColumnIndex(COLUMN_PUB_GAMES)));
                pub.setLat(cursor.getString(cursor.getColumnIndex(COLUMN_PUB_LAT)));
                pub.setLng(cursor.getString(cursor.getColumnIndex(COLUMN_PUB_LNG)));
                // Adding pub record to list
                pubList.add(pub);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return pub list
        return pubList;
    }
    public Pub getPub(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PUB, new String[] { COLUMN_PUB_ID,
                        COLUMN_PUB_NAME, COLUMN_PUB_LOCATION,COLUMN_PUB_LAT, COLUMN_PUB_LNG }, COLUMN_PUB_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Pub pub = new Pub(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        // return contact
        return pub;
    }
}
