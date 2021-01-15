package com.paz.garagemultiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.paz.common.activity.GenericGarageActivity;

public class MainActivity extends GenericGarageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackgroundColor(getResources().getColor(R.color.primary, getTheme()));
        setAppName("Garage View");
    }
}