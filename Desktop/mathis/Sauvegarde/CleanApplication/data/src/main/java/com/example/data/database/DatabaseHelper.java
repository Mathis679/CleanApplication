package com.example.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.data.database.table.UserTable;


/**
 * Created by e-Conception on 19/04/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {



    private static final String BDD_NAME = "cleanapp.db";
    private static final int BDD_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, BDD_NAME, null, BDD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        UserTable.onCreate(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        UserTable.onUpgrade(database,oldVersion,newVersion);
        this.onCreate(database);
    }

}
