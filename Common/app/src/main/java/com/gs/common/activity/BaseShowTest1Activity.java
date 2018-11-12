package com.gs.common.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gs.common.R;
import com.gs.common.activity.base.Base1Activity;

public class BaseShowTest1Activity extends Base1Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_show_test1);
    }

}
