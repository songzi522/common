package com.gs.common.interfacetest2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gs.common.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InterfaceTestActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_interface_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3,R.id.btn4, R.id.btn5, R.id.btn6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(InterfaceTestActivity.this, InterfaceTest1Activity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(InterfaceTestActivity.this, InterfaceTest2Activity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(InterfaceTestActivity.this, InterfaceTest3Activity.class));
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
