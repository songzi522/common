package com.gs.common.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gs.common.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentTest2Activity extends AppCompatActivity {

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
        setContentView(R.layout.activity_fragment_test2);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                FragmentManager FM1 = getFragmentManager();
                FragmentTransaction MfragmentTransaction1 =FM1.beginTransaction();
                Fragment01  f1 = new Fragment01();
                MfragmentTransaction1.add(R.id.fragment_buju,f1,"f1");
                MfragmentTransaction1.commit();
                break;
            case R.id.btn2:
                FragmentManager FM2 = getFragmentManager();
                FragmentTransaction MfragmentTransaction2 = FM2.beginTransaction();
                Fragment02 f2 = new Fragment02();
                MfragmentTransaction2.add(R.id.fragment_buju,f2,"f2");
                //提交事务，调用commit方法提交。
                MfragmentTransaction2.commit();
                break;
            case R.id.btn3:
                FragmentManager FM3 = getFragmentManager();
                Fragment fragment = FM3.findFragmentByTag("f2");
                FragmentTransaction MfragmentTransaction3 = FM3.beginTransaction();
                MfragmentTransaction3.remove(fragment);
                MfragmentTransaction3.commit();
                break;
            case R.id.btn4:
                FragmentManager FM4 = getFragmentManager();
                FragmentTransaction MfragmentTransaction4 = FM4.beginTransaction();
                Fragment02 f2_02 = new Fragment02();
                MfragmentTransaction4.replace(R.id.fragment_buju,f2_02);
                MfragmentTransaction4.commit();
                break;
        }
    }
}
