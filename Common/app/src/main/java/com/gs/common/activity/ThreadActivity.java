package com.gs.common.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gs.common.R;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThreadActivity extends AppCompatActivity {


    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        ButterKnife.bind(this);
    }

    Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 111) {
                btn2.setEnabled(true);
            }

        }
    };


    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                btn1.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        btn1.setEnabled(true);
                    }
                }, 2000);
                break;
            case R.id.btn2:
                btn2.setEnabled(false);
                handler2.sendEmptyMessageDelayed(111, 2000);
                break;
            case R.id.btn3:
                /**
                 * 报错：Animators may only be run on Looper threads
                 */
//                btn3.setEnabled(false);
//                new Thread() {
//                    @Override
//                    public void run() {
//                        super.run();
//                        try {
//                            Thread.sleep(2000);
//                            btn3.setEnabled(true);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }.start();

                break;
            case R.id.btn4:
                /**
                 * 报错：Animators may only be run on Looper threads
                 */
//                btn4.setEnabled(false);
//                TimerTask timerTask = new TimerTask() {
//                    @Override
//                    public void run() {
//                        btn4.setEnabled(true);
//                    }
//                };
//                Timer timer = new Timer();
//                timer.schedule(timerTask, 2000);

                break;
        }
    }


}
