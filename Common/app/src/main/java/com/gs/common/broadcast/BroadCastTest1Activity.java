package com.gs.common.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gs.common.R;
import com.gs.common.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BroadCastTest1Activity extends AppCompatActivity {

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

    MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast_test1);
        ButterKnife.bind(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.gs.data");
        myBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3,R.id.btn4, R.id.btn5})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn1:  //静态广播 manifest中静态注册
                intent.setAction("com.gs.send");
                intent.putExtra("data", "data from BroadCastTest1Activity");
                sendBroadcast(intent);
                break;
            case R.id.btn2:
                intent.setAction("com.gs.data");
                intent.putExtra("data", "data22222");
                sendBroadcast(intent);
                break;
            case R.id.btn3:
                startActivity(new Intent(BroadCastTest1Activity.this, BroadCastTest2Activity.class));
                finish();
                break;
            case R.id.btn4:
                startActivity(new Intent(BroadCastTest1Activity.this, BroadCastTest2Activity.class));
                break;
            case R.id.btn5:
                break;
        }
    }



    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra("data");
            ToastUtil.showToastShort(data);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(myBroadcastReceiver);
    }
}
