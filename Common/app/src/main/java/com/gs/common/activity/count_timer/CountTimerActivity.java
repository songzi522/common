package com.gs.common.activity.count_timer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gs.common.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CountTimerActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_timer);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3,R.id.btn4, R.id.btn5, R.id.btn6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(CountTimerActivity.this, CountTimerTest1Activity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(CountTimerActivity.this, CountTimerTest2Activity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(CountTimerActivity.this, CountTimerTest3Activity.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(CountTimerActivity.this, CountTimerTest4Activity.class));
                break;
            case R.id.btn5:
                break;
            case R.id.btn6:
                break;
        }
    }




}
