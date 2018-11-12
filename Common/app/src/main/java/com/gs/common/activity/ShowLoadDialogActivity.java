package com.gs.common.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gs.common.R;
import com.gs.common.dialog.AnimDrawableAlertDialog;
import com.gs.common.dialog.IconDrawableDialog;
import com.gs.common.utils.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ShowLoadDialogActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;

    private IconDrawableDialog loading;
    private AnimDrawableAlertDialog progressDrawableAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_load_dialog);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                UIHelper.showLoadDialog(this);
                Timer2();
                break;
            case R.id.btn2:
                loading = new IconDrawableDialog(this);
                loading.setTitle("刷新中");
                loading.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading.dismiss();
                    }
                }, 2000);
                break;
            case R.id.btn3:
                progressDrawableAlertDialog = new AnimDrawableAlertDialog(this);
                progressDrawableAlertDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDrawableAlertDialog.dismiss();
                    }
                }, 2000);
                break;
        }
    }

    Handler handler2 = new Handler();
    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            UIHelper.cloesLoadDialog();
        }
    };

    private void Timer2() {
        handler2.postDelayed(runnable2, 2000);
    }


}
