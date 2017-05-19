package com.example.mathis.cleanapplication.viewmodels;

import com.example.data.model.UserModel;

/**
 * Created by e-Conception on 18/04/2017.
 */

public class UserViewModel {
    UserModel userModel;

    public UserViewModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public int getId() {
        return userModel.getId();
    }

    public String getEmail() {
        return userModel.getEmail();
    }

    public String getFirstname() {
        return userModel.getFirstname();
    }

    public String getLastname() {
        return userModel.getLastname();
    }

    public String getDisplayName(){
        String firstname = userModel.getFirstname();
        firstname = firstname.substring(0,1).toUpperCase() + firstname.substring(1);
        return  firstname + " " + userModel.getLastname().toUpperCase();
    }

    public UserModel getUserModel(){
        return this.userModel;
    }

}
