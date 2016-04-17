package com.example.makhanov.gwusales;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by makhanov on 4/7/16.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "uKYJfFbGpuETBCEXl9YActhH2cP8we1WouQkgwav", "lSma9nxhWUqRa0whtK8jZXl4eoSK7qUZOIYZKMWL");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
