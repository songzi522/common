package com.gs.common.utils;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;

import com.gs.common.activity.count_timer.CountTimerActivity;

public class CountTimer extends CountDownTimer {

    private Context context;

    public int type;

    /**
     * 参数 millisInFuture       倒计时总时间（如60S，120s等）
     * 参数 countDownInterval    渐变时间（每次倒计1s）
     */
    public CountTimer(long millisInFuture, long countDownInterval, Context context, int type) {
        super(millisInFuture, countDownInterval);
        this.context = context;
        this.type = type;
    }


    // 计时完毕时触发
    @Override
    public void onFinish() {
        Intent intent = new Intent(context, CountTimerActivity.class);
        intent.putExtra("jump_type", type);
        context.startActivity(intent);

//        context.startActivity(new Intent(context, ShowAdsActivity.class));
    }

    // 计时过程显示
    @Override
    public void onTick(long millisUntilFinished) {

    }


    public static class JUMP_TO_SHOWADS_TYPE {
        public final static int FROM_WEBVIEW_ATY = 1;       // 从WebViewActivity跳转而来
        public final static int FROM_WX_WEBVIEW_ATY = 2;    // 从WxWebViewActivity跳转而来
    }

}