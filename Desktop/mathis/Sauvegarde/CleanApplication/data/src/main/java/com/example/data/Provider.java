package com.example.data;

import com.example.data.cache.CacheProvider;
import com.example.data.preferences.PreferencesManager;

/**
 * Created by mathis on 13/04/17.
 */

public class Provider {

    private static final Provider ourInstance = new Provider();

    public SyncData datas = SyncData.getInstance();
    public PreferencesManager preferences = PreferencesManager.getInstance();
    public CacheProvider cache = CacheProvider.getInstance();
    Asker asker;

    public static Provider getInstance() {
        return ourInstance;
    }

    private Provider() {
    }

    public void initAsker(Asker asker){
        this.asker = asker;
    }

    public void onDataChanged(){
        asker.updateViews();
    }

}
