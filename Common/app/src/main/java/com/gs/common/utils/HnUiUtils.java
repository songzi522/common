package com.gs.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.gs.common.MyApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright (C) 2016,深圳市红鸟网络科技股份有限公司 All rights reserved.
 * 项目名称：
 * 类描述：UI工具类
 * 创建人：Kevin
 * 创建时间：2016/4/26 10:53
 * 修改人：Kevin
 * 修改时间：2016/4/26 10:53
 * 修改备注：
 * Version: 1.0.0
 */
public class HnUiUtils {
    /**
     * 得到上下文
     */
    public static Context getContext() {
        return MyApplication.getContext();
    }

    /**
     * 得到Resource对象
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**得到WindowManager*/
    public static WindowManager getWindowManager() {
        return (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
    }

    /**得到Window宽*/
    public static int getWindowWidth() {
        return getWindowManager().getDefaultDisplay().getWidth();
    }

    /**得到Window宽*/
    public static int getWindowHeight() {
        return getWindowManager().getDefaultDisplay().getHeight();
    }

    /**
     * 得到string.xml中的字符
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 得到string.xml中的字符数组
     */
    public static String[] getStrings(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 得到color.xml中的颜色
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 得到应用程序的包名
     */
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**
     * 得到主线程的线程id
     *
     * @return
     */
    public static long getMainThreadId() {
        return MyApplication.getMainThreadId();
    }

    /**
     * 得到主线程的Handler对象
     */
    public static Handler getMainThreadHandler() {
        return MyApplication.getMainThreadHandler();
    }

    /**
     * 安全的执行一个任务
     * 1.当前任务所在线程子线程-->使用消息机制发送到主线程执行
     * 2.当前任务所在线程主线程-->直接执行
     */
    public static void postTaskSafely(Runnable task) {
        //得到当前线程的线程id
        long curThreadId = android.os.Process.myTid();
        long mainThreadId = getMainThreadId();
        if (curThreadId == mainThreadId) {//主线程
            task.run();
        } else {//子线程
            getMainThreadHandler().post(task);
        }
    }

    /**延迟执行任务*/
    public static void postTaskDelay(Runnable task, int delayMillis) {
        getMainThreadHandler().postDelayed(task, delayMillis);
    }

    /**移除任务*/
    public static void removeTask(Runnable task) {
        getMainThreadHandler().removeCallbacks(task);
    }


    /**
     * 将13位时间戳装换为天
     *
     * @param time
     */
    public static String timestamp2Date(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        //yyyy-MM-dd HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = null;
        if (time.length() == 13) {
            format = sdf.format(new Date(toLong(time)));
        }
        return format;
    }

    /**
     * String转long
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * @param content
     * @return
     * @description 获取一段字符串的字符个数（包含中英文，一个中文算2个字符）
     */

    public static int getCharacterNum(final String content) {
        if (null == content || "".equals(content)) {
            return 0;
        } else {
            return (content.length() + getChineseNum(content));
        }

    }

    /**
     * @param s
     * @return
     * @description 返回字符串里中文字或者全角字符的个数
     */

    private static int getChineseNum(String s) {
        int num = 0;
        char[] myChar = s.toCharArray();
        for (int i = 0; i < myChar.length; i++) {
            if ((char) (byte) myChar[i] != myChar[i]) {
                num++;
            }
        }
        return num;
    }

    /**
     * 设置全屏
     * @param activity
     */
    public static void setFullScreen(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            //全屏显示
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

}
