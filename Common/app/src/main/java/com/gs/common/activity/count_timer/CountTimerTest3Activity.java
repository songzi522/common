package com.gs.common.activity.count_timer;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.gs.common.R;
import com.gs.common.utils.CountTimer;

public class CountTimerTest3Activity extends AppCompatActivity {

    private CountTimer countTimerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_timer_test3);

        countTimerView = new CountTimer(5 * 1000, 1000, CountTimerTest3Activity.this,
                CountTimer.JUMP_TO_SHOWADS_TYPE.FROM_WX_WEBVIEW_ATY);

        timeStart();
    }

    private void timeStart() {
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                countTimerView.start();
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            //获取触摸动作，如果ACTION_UP，计时开始。
            case MotionEvent.ACTION_UP:
                countTimerView.start();
                break;
            //否则其他动作计时取消
            default:
                countTimerView.cancel();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onPause() {
        super.onPause();
        countTimerView.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        timeStart();
    }


}
