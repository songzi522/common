package com.gs.common.activity.lifeCycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gs.common.MainActivity;
import com.gs.common.R;
import com.gs.common.activity.test.TestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LifeCycleActivity extends AppCompatActivity {

    private static final String TAG = "LifeCycleActivity";

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(LifeCycleActivity.this, LifeCycleTest1Activity.class));
                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
        }
    }




}
