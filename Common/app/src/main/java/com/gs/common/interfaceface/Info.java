package com.gs.common.interfaceface;

import java.util.Date;

public class Info {

    private OnInfoFetchCallback callback;

    public Info(OnInfoFetchCallback callback) {
        this.callback = callback;
    }

    public void getInfo() {
        // 模拟一个耗时操作
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    // 信息获取成功，传递结果
                    callback.onSuccess("结果是：" + new Date());
                } catch (InterruptedException e) {
                    // 信息获取失败
                    callback.failure();
                }
            }
        });
        thread.start();
    }

}