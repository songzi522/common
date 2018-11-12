package com.gs.common.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gs.common.R;
import com.gs.common.adapter.MyDividerItemDecoration;
import com.gs.common.adapter.RvAdapter2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RvTest2Activity extends AppCompatActivity {

    @BindView(R.id.myRecyclerView)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_test2);
        ButterKnife.bind(this);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mAdapter = new RvAdapter2(getData());

        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);

        // 设置Item之间间隔样式
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));

    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for(int i = 0; i < 20; i++) {
            data.add(i + temp);
        }
        return data;
    }



}
