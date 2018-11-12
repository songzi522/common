package com.gs.common.activity.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gs.common.R;
import com.gs.common.activity.base.BaseReadyGoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends BaseReadyGoActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                readyGo(Test1Activity.class);
                break;
            case R.id.btn2:
                readyGo(Test2Activity.class);
                break;
            case R.id.btn3:
                readyGo(Test4Activity.class);
                break;
            case R.id.btn4:
                break;
            case R.id.btn5:
                break;
            case R.id.btn6:
                break;
        }

    }


}
