package com.gs.common.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.gs.common.R;

import static com.gs.common.MyApplication.getContext;

public class ToastManager {

    private static Toast mToast;// 如果定义成静态的变量，会造成内存泄露。

    public ToastManager(Context context) {
        mToast = new Toast(context.getApplicationContext());

        LayoutInflater inflate = (LayoutInflater)
                context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.toast, null);

        mToast.setView(v);
        mToast.setDuration(Toast.LENGTH_SHORT);
    }

    /**
     * @param message Toast的信息
     */
    public static void showToast(String message) {
        if (null != mToast) {
            mToast.setText(message);
            mToast.show();
        }
    }

    /**
     * @param stringId Toast信息的id
     */
    public void showToast(int stringId) {
        if (null != mToast) {
            mToast.setText(getContext().getResources().getString(stringId));
            mToast.show();
        }
    }

    public void destroy() {
        mToast = null;
    }
}