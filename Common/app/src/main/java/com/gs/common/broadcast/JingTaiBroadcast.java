package com.gs.common.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.gs.common.utils.ToastUtil;

/**
 * Created by Administrator on 2018/4/18.
 */

public class JingTaiBroadcast extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra("data");
        ToastUtil.showToastShort("This is JingTaiBroadcast. " + data);
    }


}
