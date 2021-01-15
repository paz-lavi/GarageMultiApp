package com.paz.common;

import android.app.Application;

import com.paz.common.room.MyDB;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MyDB.initHelper(getApplicationContext());
    }

}
