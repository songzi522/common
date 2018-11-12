package com.gs.common.interfacetest2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.gs.common.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InterfaceTest3Activity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;

    private DatePickDiaUtil datePickDiaUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_test3);
        ButterKnife.bind(this);

        datePickDiaUtil = new DatePickDiaUtil(this, new DatePickDiaUtil.StrPost() {
            @Override
            public void postData(String str) {
                btn1.setText(str);
            }
        });

    }

    @OnClick(R.id.btn1)
    public void onViewClicked() {
        datePickDiaUtil.showPopwindow(datePickDiaUtil.getDataPick());
    }


}
