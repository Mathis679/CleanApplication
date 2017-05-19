package com.example.data;

import android.content.Context;

import com.example.data.database.DBCaller;
import com.example.data.model.UserModel;
import com.example.data.webservices.WSCaller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by e-Conception on 18/04/2017.
 */

public class SyncData implements BaseCall{
    private static final SyncData ourInstance = new SyncData();

    static  List<UserModel> datas = new ArrayList<>();

    WSCaller wsCaller = WSCaller.getInstance();
    DBCaller dbCaller = DBCaller.getInstance();
    Provider provider = Provider.getInstance();


    static SyncData getInstance() {
        return ourInstance;
    }

    private SyncData() {
    }

    private boolean isLocalDBSyncWithWS(){
        wsCaller.onErrorResponse(new Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
        if(internet()){
            if(wsCaller.getVersionDB() > dbCaller.getVersionDB()){
                return false;
            }
            return true;
        }
        //if no network -> call local DB
        return true;

    }


    @Override
    public void addUser(UserModel userModel, Context context) {

        DBCaller.getInstance().addUser(userModel, context);
        Provider.getInstance().onDataChanged();
    }

    @Override
    public List<UserModel> getUsers(Context context) {

        datas = DBCaller.getInstance().getUsers(context);

        if(datas.size() == 0) {
            datas.add(new UserModel(0, "jean@pierre.com", "jean", "pierre"));
            datas.add(new UserModel(0, "jean@jacque.com", "jean", "jacque"));
            datas.add(new UserModel(0, "jean@alain.com", "jean", "alain"));
            datas.add(new UserModel(0, "jean@neymar.com", "jean", "neymar"));
            datas.add(new UserModel(0, "jean@paul.com", "jean", "paul"));
            datas.add(new UserModel(0, "jean@peutplus.com", "jean", "peutplus"));
            datas.add(new UserModel(0, "jean@kaaris.com", "jean", "kaaris"));
            datas.add(new UserModel(0, "jean@booba.com", "jean", "booba"));
        }
//        if(isLocalDBSyncWithWS()){
//            datas = dbCaller.getUsers();
//        }else{
//            datas = wsCaller.getUsers();
//            updateDB();
//        }

        return datas;
    }

    @Override
    public void removeUser(UserModel userModel, Context context) {
        DBCaller.getInstance().removeUser(userModel, context);
        Provider.getInstance().onDataChanged();
    }

    @Override
    public int getVersionDB() {
        return 0;
    }

    public boolean internet(){
        return true;
    }

    public void updateDB(){
        //call api fill db
        Provider.getInstance().onDataChanged();
    }


}
