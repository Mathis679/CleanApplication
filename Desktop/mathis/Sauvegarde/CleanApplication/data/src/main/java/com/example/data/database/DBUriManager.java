package com.example.data.database;

import android.net.Uri;

/**
 * Created by e-Conception on 18/05/2017.
 */

public class DBUriManager {

    protected static final String AUTHORITY = "cleanapplication.Providers.cleanapplicationDataBaseProvider";

    //BASE PATH
    protected static final String BASE_PATH_USER = "user";


    //URI TO ACCESS SPECIFIC TABLE

    public  static final Uri CONTENT_URI_USER = Uri.parse("content://" + AUTHORITY
            + '/' + BASE_PATH_USER);


    // TABLE NAME

    public static final String TABLE_USER = "user";


    // ARBITRE

    public static final String USER_ID = "ID";
    public static final String USER_FIRSTNAME = "firstnameUser";
    public static final String USER_LASTNAME = "lastnameUser";
    public static final String USER_EMAIL = "emailUser";

}
