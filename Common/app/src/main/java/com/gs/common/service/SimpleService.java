package com.gs.common.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.JsonSyntaxException;
import com.gs.common.utils.HnLogUtils;
import com.gs.common.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SimpleService extends Service {

    private static final String TAG = "SimpleService";


    /**
     * 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
     * 如果服务已在运行，则不会调用此方法。该方法只被调用一次
     */
    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        super.onCreate();
    }

    /**
     * 每次通过startService()方法启动Service时都会被回调。
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");

//        startHeartbeatAct();

        return super.onStartCommand(intent, flags, startId);

    }

    /**
     * 绑定服务时才会调用
     * 必须要实现的方法
     *
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    /**
     * 服务销毁时的回调
     */
    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

    public void startHeartbeatAct() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.e(TAG, "startHeartbeatAct");
            }
        }, 1000, 3000);
    }

}