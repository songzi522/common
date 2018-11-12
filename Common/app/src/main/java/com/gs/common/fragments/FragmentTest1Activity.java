package com.gs.common.fragments;

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

public class FragmentTest1Activity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test1);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                //获取到FragmentManager，在V4包中通过getSupportFragmentManager，
                //在系统中原生的Fragment是通过getFragmentManager获得的。
                FragmentManager FM1 = getFragmentManager();
                //2.开启一个事务，通过调用beginTransaction方法开启。
                FragmentTransaction MfragmentTransaction1 =FM1.beginTransaction();
                //把自己创建好的fragment创建一个对象
                Fragment01  f1 = new Fragment01();
                //向容器内加入Fragment，一般使用add或者replace方法实现，需要传入容器的id和Fragment的实例。
                MfragmentTransaction1.replace(R.id.fragment_buju,f1);
                //提交事务，调用commit方法提交。
                MfragmentTransaction1.commit();
                break;
            case R.id.btn2:
                //获取到FragmentManager，在V4包中通过getSupportFragmentManager，
                //在系统中原生的Fragment是通过getFragmentManager获得的。
                FragmentManager FM2 = getFragmentManager();
                //2.开启一个事务，通过调用beginTransaction方法开启。
                FragmentTransaction MfragmentTransaction2 =FM2.beginTransaction();
                //把自己创建好的fragment创建一个对象
                Fragment02  f2 = new Fragment02();
                //向容器内加入Fragment，一般使用add或者replace方法实现，需要传入容器的id和Fragment的实例。
                MfragmentTransaction2.replace(R.id.fragment_buju,f2);
                //提交事务，调用commit方法提交。
                MfragmentTransaction2.commit();
                break;
        }

    }

}
