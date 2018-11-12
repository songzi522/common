package com.gs.common.activity.count_timer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.gs.common.R;

public class CountTimerTest2Activity extends AppCompatActivity {

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_timer_test2);



        // public final void cancel ()   方法1cancel(): 取消当前的任务

        // public abstract void onFinish ()  方法2onFinish(): 当前任务完成的时候回调

        // public abstract void onTick (long millisUntilFinished)
        // 方法3onTick(long millisUntilFinished): 当前任务每完成一次倒计时间隔时间时回调

        // public final CountDownTimer start ()  方法4start(): 开始当前的任务

        // CountDownTimer (long millisInFuture, long countDownInterval)
        // 参数1: 你要倒计时的总时间, 单位ms
        // 参数2: 你要倒计时的间隔时间, 单位ms
        countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(CountTimerTest2Activity.this, CountTimerActivity.class));
            }
        };

        timeStart();
    }

    private void timeStart() {
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                countDownTimer.start();
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            //获取触摸动作，如果ACTION_UP，计时开始。
            case MotionEvent.ACTION_UP:
                countDownTimer.start();
                break;
            //否则其他动作计时取消
            default:
                countDownTimer.cancel();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
            timeStart();
    }


}
