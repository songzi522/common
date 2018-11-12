package com.gs.common.activity.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.gs.common.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Test3Activity extends AppCompatActivity {

    private static final String TAG = "Test3Activity";

    @BindView(R.id.btn1)
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        ButterKnife.bind(this);

        Log.e(TAG,"onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG,"onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");

    }


    @OnClick(R.id.btn1)
    public void onViewClicked() {
        finish();
    }
}
