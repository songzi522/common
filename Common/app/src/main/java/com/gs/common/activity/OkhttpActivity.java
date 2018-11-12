package com.gs.common.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.gs.common.R;
import com.gs.common.bean.OrgaInfoBean;
import com.gs.common.http.okhttp.OkhttpUtils;
import com.gs.common.http.okhttp.SetRequestBodyMap;

import java.io.IOException;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Response;
import okhttp3.Callback;

public class OkhttpActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhtp);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Map body = SetRequestBodyMap.set_Info2("jg100");
                OkhttpUtils.postHttp("http://service.phmd247.com/Api/V5/getorginfo", body, null, new Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                        Log.v("onFailure", e.toString());
                    }

                    @Override
                    public void onResponse(okhttp3.Call call, Response response) throws IOException {
                        /**
                         * response.body().string()只能调用一次
                         */
//                        Log.v("onResponse", response.body().string());

                        Gson gson = new Gson();
                        String json = response.body().string();
                        OrgaInfoBean bean = gson.fromJson(json, OrgaInfoBean.class);

                        Log.v("onResponse", json);
                        Log.e("哈啊哈", bean.getRet().getName() + "");

                    }

                });
                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                break;
        }
    }
}
