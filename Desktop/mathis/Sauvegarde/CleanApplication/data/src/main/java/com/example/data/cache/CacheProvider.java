package com.example.data.cache;

/**
 * Created by e-Conception on 19/04/2017.
 */

public class CacheProvider {
    private static final CacheProvider ourInstance = new CacheProvider();

    public static CacheProvider getInstance() {
        return ourInstance;
    }

    private CacheProvider() {
    }
}
