package com.example.data.preferences;

/**
 * Created by e-Conception on 19/04/2017.
 */

public class PreferencesManager {
    private static final PreferencesManager ourInstance = new PreferencesManager();

    public static PreferencesManager getInstance() {
        return ourInstance;
    }

    private PreferencesManager() {
    }
}
