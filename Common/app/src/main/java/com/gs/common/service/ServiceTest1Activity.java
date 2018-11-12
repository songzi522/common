package com.gs.common.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gs.common.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceTest1Activity extends AppCompatActivity {

    @BindView(R.id.btnStartService)
    Button btnStartService;
    @BindView(R.id.btnStopService)
    Button btnStopService;
    @BindView(R.id.btnBindService)
    Button btnBindService;
    @BindView(R.id.btnUnbindService)
    Button btnUnbindService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test1);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.btnStartService, R.id.btnStopService, R.id.btnBindService, R.id.btnUnbindService})
    public void onViewClicked(View view) {

        Intent intent = new Intent(this, SimpleService.class);

        switch (view.getId()) {
            case R.id.btnStartService:
                if (isServiceRunning("com.gs.common.service.SimpleService")) {
                    Log.e("服务正在运行", "return");
                    return;
                }
                /*启动服务*/
                startService(intent);
                break;
            case R.id.btnStopService:
                stopService(intent);
//                simpleService.stopSelf();
                break;
            case R.id.btnBindService:
                bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                unbindService(serviceConnection);
                break;
        }

    }


    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    /**
     * 判断服务是否运行
     */
    private boolean isServiceRunning(final String className) {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> info = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (info == null || info.size() == 0) return false;
        for (ActivityManager.RunningServiceInfo aInfo : info) {
            if (className.equals(aInfo.service.getClassName())) return true;
        }
        return false;
    }



}
