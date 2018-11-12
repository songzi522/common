package com.gs.common.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gs.common.R;
import com.gs.common.view.xListView.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LV2Activity extends AppCompatActivity {

    @BindView(R.id.ivBackArrow)
    ImageView ivBackArrow;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.xListView)
    XListView xListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv2);
        ButterKnife.bind(this);

        xListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, getData1()));

        //  java.lang.IllegalStateException: ArrayAdapter requires the resource ID to be a TextView
        //  xListView.setAdapter(new ArrayAdapter<>(this, R.layout.item_layout1, getData1()));

        //  没有数据
        //  xListView.setAdapter(new ArrayAdapter<>(this, R.layout.item_layout1));

    }

    private List<String> getData1() {
        List<String> data = new ArrayList<>();
        data.add("简单文本数据");
        data.add("简单文本数据");
        data.add("简单文本数据");
        return data;
    }




}
