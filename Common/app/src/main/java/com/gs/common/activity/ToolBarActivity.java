package com.gs.common.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.gs.common.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToolBarActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        ButterKnife.bind(this);

        toolbar.setTitle("Title");
        toolbar.setTitleTextColor(getResources().getColor(R.color.C1));
        toolbar.setSubtitle("SubTitle");
        toolbar.setLogo(R.mipmap.ic_launcher);

        //设置导航图标要在setSupportActionBar方法之后
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.goods_down);



    }
}
