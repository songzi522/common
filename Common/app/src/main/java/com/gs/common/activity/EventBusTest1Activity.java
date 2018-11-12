package com.gs.common.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gs.common.R;
import com.gs.common.bean.MessageEvent;
import com.gs.common.bean.MessageEventBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusTest1Activity extends AppCompatActivity {

    @BindView(R.id.tvMessage)
    TextView tvMessage;
    @BindView(R.id.btnMessage)
    Button btnMessage;
    @BindView(R.id.btnubscription)
    Button btnubscription;
    @BindView(R.id.btnMessage2)
    Button btnMessage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_test1);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);
    }


    @OnClick({R.id.btnMessage, R.id.btnubscription, R.id.btnMessage2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnMessage:
                startActivity(new Intent(EventBusTest1Activity.this, EventBusTest2Activity.class));
                break;
            case R.id.btnubscription:
                //注册事件
                if (!EventBus.getDefault().isRegistered(EventBusTest1Activity.this)) {
                    EventBus.getDefault().register(EventBusTest1Activity.this);
                } else {
                    Toast.makeText(EventBusTest1Activity.this, "请勿重复注册事件", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnMessage2:
                startActivity(new Intent(EventBusTest1Activity.this, EventBusTest3Activity.class));
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEventBean bean) {
        if (bean.getType() == MessageEventBean.TYPE_ONE) {
            tvMessage.setText(bean.getMessage() + ", " + bean.getName() + ", " + bean.getAge());
        }

    }

    @Subscribe(sticky = true)
    public void onMoonStickyEvent(MessageEvent messageEvent) {
        tvMessage.setText(messageEvent.getMessage());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
