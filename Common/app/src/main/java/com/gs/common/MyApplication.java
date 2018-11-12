package com.gs.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.gs.common.http.okhttp.OkHttpSingleton;


/**
 * Created by Administrator on 2017/11/22.
 */

public class MyApplication extends Application {

    private static MyApplication myAPPlication;

    private static Context mContext;
    private static int     mMainThreadId;
    private static Handler mMainThreadHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        myAPPlication = this;
        OkHttpSingleton.getInstance().init(this);

        /**上下文*/
        mContext = getApplicationContext();

        /**主线程ID*/
        mMainThreadId = android.os.Process.myTid();

        /**主线程Handler*/
        mMainThreadHandler = new Handler();

    }

    public static Context getInstance() {
        return myAPPlication;
    }

    /**
     * get方法
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * 获取主线程id
     * @return
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程
     * @return
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }




}
