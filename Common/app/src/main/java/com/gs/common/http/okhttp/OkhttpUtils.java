package com.gs.common.http.okhttp;

import android.util.Log;

import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2016/11/30.
 */

public class OkhttpUtils {
    static String CameraDataJSON;
    private static boolean loginRequet;
    private static Callback callback;


    private static final String TAG = "OkhttpUtils";



    /**
     * post请求参数
     *
     * @param BodyParams
     * @return
     */
    private static RequestBody SetRequestBody(Map<String, String> BodyParams) {
        RequestBody body = null;
        FormBody.Builder formEncodingBuilder = new FormBody.Builder();
        if (BodyParams != null) {
            Iterator<String> iterator = BodyParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                formEncodingBuilder.add(key, BodyParams.get(key));
                Log.d(TAG, "post_Params===" + key + "====" + BodyParams.get(key));
            }
        }
        body = formEncodingBuilder.build();
        return body;

    }

    /**
     * get方法连接拼加参数
     *
     * @param mapParams
     * @return
     */
    private static String setUrlParams(Map<String, String> mapParams) {
        String strParams = "";
        if (mapParams != null) {
            Iterator<String> iterator = mapParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                strParams += "&" + key + "=" + mapParams.get(key);
            }
        }

        return strParams;
    }


    /**
     * post请求
     *
     * @param BodyParams
     * @return
     */

    public static void postHttp(String reqUrl, Map<String, String> BodyParams, Object object, Callback callback) {
        OkHttpClient okHttpClient = OkHttpSingleton.getmClient();
        Request.Builder RequestBuilder = new Request.Builder();
        RequestBuilder.url(reqUrl);//添加URL地址
        RequestBuilder.post(SetRequestBody(BodyParams));
//        RequestBuilder.headers(SetHeaders(headersParams));//添加请求头
        RequestBuilder.tag(object);//添加请求标签
        Request request = RequestBuilder.build();
        Log.d("post http", "post_url===" + request.url());
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }


    /**
     * get请求
     *
     * @param BodyParams
     * @return
     */
    public static void getHttp(String reqUrl, Map<String, String> BodyParams, Object object, Callback callback) {
        OkHttpClient okHttpClient = OkHttpSingleton.getmClient();
        Request request = null;
        if (BodyParams != null) {
            request = new Request.Builder().url(reqUrl + "?" + setUrlParams(BodyParams))
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Accept", "application/json").build();
        } else {
            request = new Request.Builder().url(reqUrl)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Accept", "application/json").build();
        }
        okHttpClient.newCall(request).enqueue(callback);

    }
}
