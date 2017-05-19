package com.example.data.database;

import android.content.Context;

import com.example.data.BaseCall;
import com.example.data.database.dao.UserDAO;
import com.example.data.model.BaseModel;
import com.example.data.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by e-Conception on 18/04/2017.
 */

public class DBCaller implements BaseCall{

    private static final DBCaller ourInstance = new DBCaller();

    public static DBCaller getInstance() {
        return ourInstance;
    }

    private DBCaller() {
    }

    @Override
    public void addUser(UserModel userModel, Context context) {
        UserDAO userDAO = new UserDAO(context);
        userDAO.add(userModel);
    }

    public List<UserModel> getUsers(Context context){

        UserDAO userDAO = new UserDAO(context);
        return userDAO.getAll();
    }

    @Override
    public void removeUser(UserModel userModel, Context context) {
        UserDAO userDAO = new UserDAO(context);
        userDAO.delete(userModel);
    }

    public int getVersionDB(){
        //request DB return DB version number

        int versionNumber = 1;
        return versionNumber;
    }


}
