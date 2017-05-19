package com.example.data.webservices;

import android.content.Context;

import com.example.data.BaseCall;
import com.example.data.model.UserModel;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by e-Conception on 18/04/2017.
 */

public class WSCaller implements BaseCall{
    private static final WSCaller ourInstance = new WSCaller();

    public static WSCaller getInstance() {
        return ourInstance;
    }

    private Callable funcOnErrorResult;

    private WSCaller() {
    }


    @Override
    public void addUser(UserModel userModel, Context context) {

    }

    @Override
    public List<UserModel> getUsers(Context context) {
        //request API return String content
        //transform json response to list of usermodel
        return null;
    }

    @Override
    public void removeUser(UserModel userModel, Context context) {

    }

    @Override
    public int getVersionDB()  {
        //request API return DB version number
        int response = 404;
        if(response == MyWSHelper.RESPONSE_ERROR){
            if(funcOnErrorResult != null){
                try {
                    funcOnErrorResult.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        int versionNumber = 1;
        return versionNumber;

    }

    public void onErrorResponse(Callable func){
        funcOnErrorResult = func;
    }


}
