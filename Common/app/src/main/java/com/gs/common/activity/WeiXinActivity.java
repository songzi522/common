package com.gs.common.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gs.common.R;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WeiXinActivity extends AppCompatActivity {

    //APP_ID 替换为你的应用从官方网站申请到的合法 appid
    private static final String APP_ID = "";

    // IWXAPI 是第三方APP和微信通信的openapi接口
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin);

        //通过WXAPIFactory，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(WeiXinActivity.this, APP_ID, true);
        //将应用的appid注册到微信
        api.registerApp(APP_ID);
    }

    private void method1(String text) {
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;


    }


}
