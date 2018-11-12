package com.gs.common.interfacetest2;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gs.common.R;
import com.gs.common.interfaceface.Info;
import com.gs.common.interfaceface.OnInfoFetchCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 在InterfaceTest1Activity中调用InfoService中的getInfo()方法执行耗时操作。
 * <p>
 * 由于InterfaceTest1Activity已经实现了OnInfoFetchCallback 接口，所以在实例化InfoService对象时，直接将this传入即可。
 * 当任务执行结束后，调用InterfaceTest1Activity中的onSuccess(String info)或failure()方法将结果返回。
 */

public class InterfaceTest1Activity extends AppCompatActivity implements OnInfoFetchCallback {

    @BindView(R.id.btn1)
    Button btn1;

    private static final String TAG = "InterfaceTest1Activity";

    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_test1);
        ButterKnife.bind(this);

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @OnClick(R.id.btn1)
    public void onViewClicked() {
        fetchInfo();
    }

    /**
     * 获取信息
     */
    public void fetchInfo() {
        Info info = new Info(this);
        info.getInfo();
    }

    @Override
    public void onSuccess(final String info) {
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                btn1.setText(info);
            }
        });
    }

    @Override
    public void failure() {
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                btn1.setText("获取信息失败");
            }
        });
    }


}
