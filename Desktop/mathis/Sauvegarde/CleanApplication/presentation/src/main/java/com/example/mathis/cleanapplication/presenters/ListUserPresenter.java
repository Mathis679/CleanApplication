package com.example.mathis.cleanapplication.presenters;


import android.support.v4.app.FragmentTransaction;

import com.example.data.Asker;
import com.example.data.Provider;
import com.example.data.SyncData;
import com.example.data.model.UserModel;
import com.example.mathis.cleanapplication.R;
import com.example.mathis.cleanapplication.activities.MainActivity;
import com.example.mathis.cleanapplication.fragments.ListUserFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by e-Conception on 18/04/2017.
 */

public class ListUserPresenter implements Asker {

    Provider provider = Provider.getInstance();
    List<UserModel> list;
    ListUserFragment listUserFragment;
    MainActivity mainActivity;

    public ListUserPresenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        provider.initAsker(this);
        list = provider.datas.getUsers(mainActivity);
        displayFragment();
    }

    @Override
    public void updateViews() {
        refreshDatas();
    }

    public void refreshDatas(){
        list = Provider.getInstance().datas.getUsers(mainActivity);
        listUserFragment.updateViews();
    }

    public void displayFragment(){
        FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();
        listUserFragment = ListUserFragment.newInstance((ArrayList<UserModel>)list);
        listUserFragment.setPresenter(this);
        ft.add(R.id.fragmentcontainer, listUserFragment,ListUserFragment.class.getName());
        ft.commit();
    }

    @Override
    public void newUserAdded(UserModel userModel){
        Provider.getInstance().datas.addUser(userModel,mainActivity);
    }

}
