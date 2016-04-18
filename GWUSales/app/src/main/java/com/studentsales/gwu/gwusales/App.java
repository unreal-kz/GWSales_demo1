package com.studentsales.gwu.gwusales;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Baubek on 04/13/16.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "uKYJfFbGpuETBCEXl9YActhH2cP8we1WouQkgwav", "lSma9nxhWUqRa0whtK8jZXl4eoSK7qUZOIYZKMWL");
    }
}
