package com.example.data.database.table;

import android.database.sqlite.SQLiteDatabase;

import com.example.data.database.DBUriManager;

/**
 * Created by e-Conception on 18/05/2017.
 */

public class UserTable {

    private static final String BDD_CREATE = "CREATE TABLE "
            + DBUriManager.TABLE_USER + " ("
            + DBUriManager.USER_ID + " integer primary key, "
            + DBUriManager.USER_FIRSTNAME + " text not null, "
            + DBUriManager.USER_LASTNAME + " text not null, "
            + DBUriManager.USER_EMAIL + " text not null "
            + ");";

    public static void onCreate(SQLiteDatabase database){
        database.execSQL(BDD_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE " + DBUriManager.TABLE_USER + ";");
    }
}
