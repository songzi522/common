package com.gs.common.animation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.gs.common.R;
import com.gs.common.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationTest1Activity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.iv1)
    ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test1);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.iv1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startAnimation1();
                break;
            case R.id.btn2:
                startAnimation2();
                break;
            case R.id.iv1:
                ToastUtil.showToastShort("点击了iv1");
                break;
        }
    }

    //1,使用补间动画
    private void startAnimation1() {
        TranslateAnimation mAnimation = new TranslateAnimation(0, 300, 0, 0);
        mAnimation.setDuration(1000);
        mAnimation.setFillAfter(true);
        iv1.startAnimation(mAnimation);
    }

    //2,使用属性动画
    private void startAnimation2() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv1, "translationX", 0f, 300f);
        animator.setDuration(1000);
        animator.start();
    }



}
