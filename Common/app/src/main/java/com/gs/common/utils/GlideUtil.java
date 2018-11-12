package com.gs.common.utils;

import android.app.Activity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by Administrator on 2018/1/3.
 */

public class GlideUtil {

    public static void LoadImgToBackground(Activity activity, Object img, final View view){
        Glide.with(activity)
                .load(img)
                .into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                view.setBackgroundDrawable(resource);
            }
        });

    }

}
