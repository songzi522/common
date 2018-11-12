package com.gs.common.utils;

import android.content.Context;
import android.widget.Toast;


/**
 * Copyright (C) 2016,深圳市红鸟网络科技股份有限公司 All rights reserved.
 * 项目名称：
 * 类描述： Toast工具类
 * 创建人：Kevin
 * 创建时间：2016/5/13 9:43
 * 修改人：Kevin
 * 修改时间：2016/5/13 9:43
 * 修改备注：
 * Version:  1.0.0
 */
public class HnToast {
    private static Context context = HnUiUtils.getContext();

    /**
     * 传入string类型  时间短
     *
     * @param msg
     */
    public static void showToastShort(String msg) {
        showToast(context, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 传人string类型 时间长
     *
     * @param msg
     */
    public static void showToastLong(String msg) {
        showToast(context, msg, Toast.LENGTH_LONG);
    }

    /**
     * 传人int类型 时间短
     *
     * @param strRes
     */
    public static void showToastShort(int strRes) {
        showToast(context, context.getString(strRes), Toast.LENGTH_SHORT);
    }

    /**
     * 传人int类型 时间长
     *
     * @param strRes
     */
    public static void showToastLong(int strRes) {
        showToast(context, context.getString(strRes), Toast.LENGTH_LONG);
    }

    public static void showToast(Context context, String msg, int duration) {
        Toast.makeText(context, msg, duration).show();
    }


}
