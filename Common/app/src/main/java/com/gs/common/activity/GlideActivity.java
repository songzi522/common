package com.gs.common.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.gs.common.R;
import com.gs.common.utils.GlideUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlideActivity extends AppCompatActivity {

    @BindView(R.id.rl_activity_glide)
    RelativeLayout rlActivityGlide;
    @BindView(R.id.rlBottomBanner)
    RelativeLayout rlBottomBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);


//        GlideUtil.LoadImgToBackground(GlideActivity.this, R.mipmap.bg01, rlActivityGlide);

        GlideUtil.LoadImgToBackground(GlideActivity.this,
                "http://phdzyapi.phmd247.com/uploads/0/20171214/ec52d92a2312ef50bacd5491c1bfced0.png",
                rlBottomBanner);


    }


}
