package com.gs.common.http;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Administrator on 2016/6/27.
 */
public class RequestClient {

    /**
     * 定义一个异步网络客户端 默认超时未10秒 当超过，默认重连次数为5次 默认最大连接数为10个
     */
    private static final int TIMEOUT_SECOND = 10000;
    private static final String TAG = "RequestClient";
    private static AsyncHttpClient mClient = null;

    static {
        /* 设置连接和响应超时时间 */
        mClient = new AsyncHttpClient();
        mClient.setTimeout(TIMEOUT_SECOND);
    }

    public static void post(String url, AsyncHttpResponseHandler mHandler) {
        post(url, null, mHandler);
    }

    //请求
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler mHandler) {
        /* 将参数顺序传递 */
        if (params != null) {
            Log.i(TAG, "发起post请求:" + url + "?" + params.toString());
        } else {
            Log.i(TAG, "发起post请求:" + url);
        }
        mClient.post(url, params, mHandler);
    }

    public static void get(String url, AsyncHttpResponseHandler mHandler) {
        get(url, null, mHandler);
    }

    private static void get(String url, RequestParams params, AsyncHttpResponseHandler mHandler) {
        /* 将参数顺序传递 */
        if (params != null) {
            Log.i(TAG, "发起get请求:" + url + "?" + params.toString());
        } else {
            Log.i(TAG, "发起get请求:" + url);
        }
        mClient.get(url, params, mHandler);
    }

    //获取通告列表
    public static void getAnnounceData(String page, String rows, AsyncHttpResponseHandler mHandler) {
        RequestParams params = new RequestParams();
        params.put("page", page);
        params.put("rows", rows);
        post(Urls.COMP_ANNOUNCE, params, mHandler);
    }

    public static void postTestData(String deviceid, AsyncHttpResponseHandler mHandler) {
        RequestParams params = new RequestParams();
        params.put("deviceid", deviceid);
//        post(Urls.SERVER_HOST_TEST, params, mHandler);
        post("http://phdzyapi.phmd247.com/dzy/v1/post/activate", params, mHandler);
    }

    public static void getTestData(String deviceid, AsyncHttpResponseHandler mHandler) {
        RequestParams params = new RequestParams();
        params.put("deviceid", deviceid);
        get(Urls.SERVER_HOST_TEST, params, mHandler);
    }




}
