package com.example.data.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.data.database.DBUriManager;
import com.example.data.database.DatabaseHelper;
import com.example.data.model.BaseModel;
import com.example.data.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathis on 13/04/17.
 */

public class UserDAO {

    private DatabaseHelper databaseHelper;
    private boolean used;

    public UserDAO(Context context){
        this.databaseHelper = new DatabaseHelper(context);
        this.used = false;
    }

    public List<UserModel> getAll() {
        List<UserModel> userModelList = null;
        if(!used){
            used = true;
            SQLiteDatabase db = databaseHelper.getReadableDatabase();

            String selectQuery = "SELECT * " +
                    " FROM " + DBUriManager.TABLE_USER;

            userModelList = new ArrayList<>();

            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    UserModel userModel = cursorToObject(cursor);
                    userModelList.add(userModel);
                } while (cursor.moveToNext());
            }else{
                userModelList = null;
            }
            cursor.close();
            db.close();
            used=false;
        }
        return userModelList;
    }

    public UserModel get(int id){
        UserModel userModel = null;
        if(!used){
            used = true;
            SQLiteDatabase db = databaseHelper.getReadableDatabase();

            String selectQuery = "SELECT * " +
                    " FROM " + DBUriManager.TABLE_USER +
                    " WHERE " + DBUriManager.USER_ID + "=?";

            Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

            if (cursor.moveToFirst()) {
                userModel = cursorToObject(cursor);
            }else{
                userModel = null;
            }
            cursor.close();
            db.close();
            used = false;
        }
        return userModel;
    }

    public int add(UserModel userModel) {
        int userId = -1;
        if(!used){
            used = true;
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DBUriManager.USER_ID, userModel.getId());
            values.put(DBUriManager.USER_FIRSTNAME, userModel.getFirstname());
            values.put(DBUriManager.USER_LASTNAME, userModel.getLastname());
            values.put(DBUriManager.USER_EMAIL, userModel.getEmail());
            userId = (int) db.insert(DBUriManager.TABLE_USER, null, values);
            db.close();
            used =false;
        }
        return userId;
    }

    public void update(UserModel userModel) {
        if(!used){
            used= true;
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DBUriManager.USER_FIRSTNAME, userModel.getFirstname());
            values.put(DBUriManager.USER_LASTNAME, userModel.getLastname());
            values.put(DBUriManager.USER_EMAIL, userModel.getEmail());
            db.update(DBUriManager.TABLE_USER, values, DBUriManager.USER_ID + "= ?", new String[]{String.valueOf(userModel.getId())});
            db.close();
            used=false;
        }
    }

    public void delete(UserModel userModel) {
        if(used == false) {
            used = true;
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            db.delete(DBUriManager.TABLE_USER, DBUriManager.USER_ID + "=?", new String[]{String.valueOf(userModel.getId())});
            db.close();
            used = false;
        }
    }

    public UserModel cursorToObject(Cursor cursor) {
        UserModel userModel = new UserModel();
        userModel.setId(cursor.getInt(cursor.getColumnIndex(DBUriManager.USER_ID)));
        userModel.setFirstname(cursor.getString(cursor.getColumnIndex(DBUriManager.USER_FIRSTNAME)));
        userModel.setLastname(cursor.getString(cursor.getColumnIndex(DBUriManager.USER_LASTNAME)));
        userModel.setEmail(cursor.getString(cursor.getColumnIndex(DBUriManager.USER_EMAIL)));
        return userModel;
    }


}
