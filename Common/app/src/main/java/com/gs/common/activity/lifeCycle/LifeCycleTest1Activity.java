package com.gs.common.activity.lifeCycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gs.common.R;

public class LifeCycleTest1Activity extends AppCompatActivity {

    private static final String TAG = "LifeCycleTest1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_test1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause()");
    }

}
