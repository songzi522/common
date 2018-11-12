package com.gs.common.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gs.common.R;
import com.gs.common.adapter.MyDividerItemDecoration;
import com.gs.common.adapter.RvAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RvTest1Activity extends AppCompatActivity {

    @BindView(R.id.myRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnDelete)
    Button btnDelete;

    private RvAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_test1);
        ButterKnife.bind(this);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new RvAdapter(getData());

        mAdapter.setOnItemClickListener(new RvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RvTest1Activity.this,"click " + position + " item", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(RvTest1Activity.this,"long click " + position + " item", Toast.LENGTH_SHORT).show();
            }
        });

        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // 设置Item添加和移除的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for (int i = 0; i < 20; i++) {
            data.add(i + temp);
        }

        return data;
    }




    @OnClick({R.id.btnAdd, R.id.btnDelete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                mAdapter.addNewItem();
                // 由于Adapter内部是直接在首个Item位置做增加操作，增加完毕后列表移动到首个Item位置
                mLayoutManager.scrollToPosition(0);
                break;
            case R.id.btnDelete:
                mAdapter.deleteItem();
                // 由于Adapter内部是直接在首个Item位置做删除操作，删除完毕后列表移动到首个Item位置
                mLayoutManager.scrollToPosition(0);
                break;
        }
    }


}
