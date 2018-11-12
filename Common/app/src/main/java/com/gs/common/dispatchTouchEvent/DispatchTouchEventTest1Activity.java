package com.gs.common.dispatchTouchEvent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.gs.common.R;

public class DispatchTouchEventTest1Activity extends AppCompatActivity {

    private static final String TAG = "DispatchTouchEventTest1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_touch_event_test1);


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "================ Action down ==============");
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "================ Action move ==============");
                return true;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "================ Action up ==============");
                return true;

            default:
                break;
        }

        return super.onTouchEvent(event);

    }





}
