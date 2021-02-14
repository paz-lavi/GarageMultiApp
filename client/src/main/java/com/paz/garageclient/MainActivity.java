package com.paz.garageclient;

import android.os.Bundle;

import com.paz.common.activity.GenericGarageActivity;

public class MainActivity extends GenericGarageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackgroundColor(getResources().getColor(R.color.primary, getTheme()));
        setAppName("Client View");

    }
}


