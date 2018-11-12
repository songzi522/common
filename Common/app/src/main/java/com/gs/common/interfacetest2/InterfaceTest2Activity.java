package com.gs.common.interfacetest2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.gs.common.R;
import com.gs.common.interfaceface.Info;
import com.gs.common.interfaceface.OnInfoFetchCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 也可以不用实现OnInfoFetchCallback 接口，此时可以采用匿名内部类的写法。
 */
public class InterfaceTest2Activity extends AppCompatActivity {

    private static final String TAG = "InterfaceTest2Activity";

    @BindView(R.id.btn1)
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_test2);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn1)
    public void onViewClicked() {
        fetchInfo();
    }


    /**
     * 获取信息
     */
    public void fetchInfo() {
        Info service = new Info(new OnInfoFetchCallback() {
            @Override
            public void onSuccess(String info) {
                Log.e(TAG, info);
            }

            @Override
            public void failure() {
                Log.e(TAG, "获取信息失败");
            }
        });
        service.getInfo();
    }


}
