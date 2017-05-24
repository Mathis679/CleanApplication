package com.example.data;

import android.content.Context;

import com.example.data.model.UserModel;

import java.util.List;

/**
 * Created by e-Conception on 18/04/2017.
 */

public interface BaseCall {

    void addUser(UserModel userModel, Context context);
    List<UserModel> getUsers(Context context);
    void removeUser(UserModel userModel, Context context);
    void updateUser(UserModel userModel, Context context);
    int getVersionDB();
}
