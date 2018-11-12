package com.gs.common.interfaceface;

/**
 * 使用3： 用于回调
 * 我们知道，一般情况下主线程是不执行耗时任务的，如果遇到一些耗时任务（如网络请求，文件读写，数据库读写等等），
 * 我们会将其放入子线程中去执行，当执行完毕后，子线程再将执行结果返回给主线程。这个过程就是回调。
 *
 * 看一个例子。
 * 首先定义一个回调接口。
 */
public interface OnInfoFetchCallback {
    /**
     * 获取信息成功
     */
    void onSuccess(String info);

    /**
     * 获取信息失败
     */
    void failure();

}