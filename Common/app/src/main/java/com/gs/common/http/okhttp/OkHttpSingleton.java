package com.gs.common.http.okhttp;

import android.content.Context;

import com.gs.common.MyApplication;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;


/**
 * Created by Administrator on 2017/11/24.
 */

public class OkHttpSingleton {

    private OkHttpSingleton okHttpSingleton;
    private Context mContext;
    private static OkHttpClient mClient;


    public static OkHttpClient getmClient() {
        return mClient;
    }


    public void init(Context context) {
        mContext = context;
        mClient = new OkHttpClient();
        OkHttpClient.Builder builder = mClient.newBuilder();

//        // 添加日志拦截
//        if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            builder.addInterceptor(loggingInterceptor);
//        }
        // 默认都是10s
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        // 添加Cookie机制
        builder.cookieJar(mCookieJar);
        mClient = builder.build();
    }



    private CookieJar mCookieJar = new CookieJar() {
        private PersistentCookieStore cookieStore = new PersistentCookieStore(MyApplication.getInstance());
        final HttpUrl REGISTER_URL = HttpUrl.parse("http://120.24.18.225:8080/hank_cloud_platform_api/user/reg");

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            if (cookies != null && cookies.size() > 0) {
                for (Cookie item : cookies) {
                    cookieStore.add(url, item);
                }
            }
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {

                List<Cookie> cookies = cookieStore.get(REGISTER_URL);
                if (cookies.size() <= 0) {
                    cookies = cookieStore.get(REGISTER_URL);
                }
                return cookies;

        }
    };



    private static class Holder {
        private static OkHttpSingleton instance = new OkHttpSingleton();
    }

    public static OkHttpSingleton getInstance() {
        return Holder.instance;
    }


}
