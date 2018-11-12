package com.gs.common.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gs.common.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceBindTest2Activity extends AppCompatActivity {

    @BindView(R.id.btnBindService)
    Button btnBindService;
    @BindView(R.id.btnUnbindService)
    Button btnUnbindService;
    @BindView(R.id.btnGetDatas)
    Button btnGetDatas;

    private static final String TAG = "ServiceBindTest2Aty";

    private ServiceConnection conn;
    private LocalService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_bind_test2);
        ButterKnife.bind(this);

        conn = new ServiceConnection() {
            /**
             * 与服务器端交互的接口方法 绑定服务的时候被回调，在这个方法获取绑定Service传递过来的IBinder对象，
             * 通过这个IBinder对象，实现宿主和Service的交互。
             */
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e(TAG, "绑定成功调用：onServiceConnected");
                // 获取Binder
                LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
                mService = binder.getService();
            }

            /**
             * 当取消绑定的时候被回调。但正常情况下是不被调用的，它的调用时机是当Service服务被意外销毁时，
             * 例如内存的资源不足时这个方法才被自动调用。
             */
            @Override
            public void onServiceDisconnected(ComponentName name) {
                mService = null;
            }
        };

    }

    @OnClick({R.id.btnBindService, R.id.btnUnbindService, R.id.btnGetDatas})
    public void onViewClicked(View view) {

        //创建绑定对象
        final Intent intent = new Intent(this, LocalService.class);

        switch (view.getId()) {
            case R.id.btnBindService:
                Log.e(TAG, "绑定调用：bindService");
                //调用绑定方法
                bindService(intent, conn, Service.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                Log.e(TAG, "解除绑定调用：unbindService");
                // 解除绑定
                if (mService != null) {
                    mService = null;
                    unbindService(conn);
                }
                break;
            case R.id.btnGetDatas:
                if (mService != null) {
                    // 通过绑定服务传递的Binder对象，获取Service暴露出来的数据
                    Log.e(TAG, "从服务端获取数据：" + mService.getCount());
                } else {
                    Log.e(TAG, "还没绑定呢，先绑定,无法从服务端获取数据");
                }
                break;
        }
    }


}
