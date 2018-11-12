package com.gs.common.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gs.common.R;
import com.gs.common.utils.ToastManager;
import com.gs.common.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToastActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;

    private ToastManager toastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        ButterKnife.bind(this);

        toastManager = new ToastManager(ToastActivity.this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Toast.makeText(ToastActivity.this, "ToastShort", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                Toast.makeText(ToastActivity.this, "ToastLong", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn3:
                ToastUtil.showToastShort("11111");
                break;
            case R.id.btn4:
//                toastManager.showToast("22222");
                ToastManager.showToast("33333");
                break;
        }
    }

}
