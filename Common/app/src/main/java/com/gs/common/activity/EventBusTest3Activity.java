package com.gs.common.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.gs.common.R;
import com.gs.common.bean.MessageEventBean;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusTest3Activity extends AppCompatActivity {

    @BindView(R.id.tvMessage)
    TextView tvMessage;
    @BindView(R.id.btnMessage)
    Button btnMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_test3);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnMessage)
    public void onViewClicked() {
        EventBus.getDefault().post(new MessageEventBean("Hello World 2", "小满2", 2, MessageEventBean.TYPE_TWO));
        finish();
    }

}
