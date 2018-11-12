package com.gs.common.activity.count_timer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gs.common.R;
import com.gs.common.utils.TimeUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CountTimerTest1Activity extends AppCompatActivity {

    @BindView(R.id.tvTimer1)
    TextView tvTimer1;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.tvTimer2)
    TextView tvTimer2;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.tvTimer3)
    TextView tvTimer3;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.tvTimer4)
    TextView tvTimer4;
    @BindView(R.id.btn4)
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_timer_test1);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Timer1();
                break;
            case R.id.btn2:
                Timer2();
                break;
            case R.id.btn3:
                Timer3();
                break;
            case R.id.btn4:
                Timer4();
                break;
        }
    }

    /**
     *第一种:Timer和TimerTask结合
     */
    //time为Date类型：在指定时间执行一次。
    //timer.schedule(task, time);

    //firstTime为Date类型,period为long，表示从firstTime时刻开始，每隔period毫秒执行一次。
    //timer.schedule(task, firstTime,period);

    //delay 为long类型：从现在起过delay毫秒执行一次。
    // timer.schedule(task, delay);

    //delay为long,period为long：从现在起过delay毫秒以后，每隔period毫秒执行一次。
    //timer.schedule(task, delay,period);
    private void Timer1() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvTimer1.setText("Timer1-->" + TimeUtils.getCurrentTime());
                    }
                });
            }
        };
        //firstTime为Date类型,period为long，表示从firstTime时刻开始，每隔period毫秒执行一次。
        //schedule(task, firstTime, period)
        timer.schedule(task, 2000, 1000);

//        别忘了在onDestroy()中
//        if (timer1 != null) {
//            timer1.cancel();
//        }

    }


    /**
     * 第二种.Handler自带的postDelayed(Runnable r, long delayMillis)方法
     */
//    Timer2()方法中的handler2.postDelayed(runnable2, 1000);和runnable2的run()中的handler2.postDelayed(this, 1000);
//    都为延时一秒钟执行runnable2,
//    Timer2()中postDelayed为入口,handler2中的postDelayed让自己陷入死循环,这种方法要记得在onDestroy()中
//    handler2.removeCallbacks(runnable2);

    Handler handler2 = new Handler();
    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            handler2.postDelayed(this, 1000);
            tvTimer2.setText("Timer2-->" + TimeUtils.getCurrentTime());
        }
    };

    private void Timer2() {
        handler2.postDelayed(runnable2, 1000);
    }


    /**
     * 第三种:最神经病的写法(适合简单的UI变化)
     */
    Handler handler3 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                tvTimer3.setText("Timer3-->" + TimeUtils.getCurrentTime());
                this.sendEmptyMessageDelayed(0, 1000);
            }

        }
    };

    private void Timer3() {
        handler3.sendEmptyMessageDelayed(0, 1000);
    }

    /**
     * 第四种:利用Thread.sleep(time);
     */
    Handler handler4 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                tvTimer4.setText("Timer4-->" + TimeUtils.getCurrentTime());
            }
        }
    };

    public class Timer4Thread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 0;
                    handler4.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void Timer4() {
        new Thread(new Timer4Thread()).start();
    }



}
