package com.gs.common.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gs.common.R;
import com.gs.common.bean.MessageEvent;
import com.gs.common.bean.MessageEventBean;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusTest2Activity extends AppCompatActivity {

    @BindView(R.id.tvMessage)
    TextView tvMessage;
    @BindView(R.id.btnMessage)
    Button btnMessage;
    @BindView(R.id.btnMessage2)
    Button btnMessage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_test2);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btnMessage, R.id.btnMessage2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnMessage:
                EventBus.getDefault().post(new MessageEventBean("Hello World1", "小满1", 1, MessageEventBean.TYPE_ONE));
                finish();
                break;
            case R.id.btnMessage2:
                EventBus.getDefault().postSticky(new MessageEvent("粘性事件"));
                finish();
                break;
        }
    }
}
