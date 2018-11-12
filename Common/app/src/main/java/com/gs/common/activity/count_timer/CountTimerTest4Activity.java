package com.gs.common.activity.count_timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gs.common.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.button;

public class CountTimerTest4Activity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_timer_test4);
        ButterKnife.bind(this);

        //拿到countDownTimer对象,参数1:一共多少毫秒,参数2:间隔多少毫秒 这个间隔表示每隔多久会进行一次onTick()方法
        // 中的操作
        countDownTimer = new CountDownTimer(11000, 2000) {
            @Override
            public void onTick(long millisUntilFinished) { // 参数2 为1000的话，表示该方法每隔1s被执行一次
                //让按钮不能被点击
                btn1.setEnabled(false);
                //设置显示倒计时时间
                btn1.setText(millisUntilFinished / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                //倒计时结束之后恢复text显示
                btn1.setText("开始倒计时");
                //让按钮重新可以点击
                btn1.setEnabled(true);
            }

        };

    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                countDownTimer.start();
                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                break;
        }
    }

}
