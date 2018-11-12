package com.gs.common.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/12/22.
 */

public class CustomViewPager extends ViewPager {

    private boolean isCanScroll = false;

    private float downX = 0;
    private float downY = 0;

    public CustomViewPager(Context context) {
        this(context, null);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIsCanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    /**
     * 不响应原因:
     ViewPager 嵌套ViewPager或者嵌套ViewFlipper都不能正确响应滑动事件,皆因嵌套的子view在TouchEvent传递过程中没有消费此次事 件，
     而由parent View消费了事件，从而在后续的TouchEvent (ACTION_MOVE或者ACTION_UP)传递过程中就不会传递到子view的onTouchEvent那，所
     以无法响应触摸事件。

     Intercept：拦截

     * 如果上层onInterceptTouchEvent  返回 false  那么就由本层 onTouchEvent 处理
     * 如果本层onTouchEvent返回true  则表示消费这个事件
     * 如果上层onInterceptTouchEvent  返回 true 所有的move  down都让本层给截取了  也就不往下传递了
     *
     *
     * 这个自定义如果继承的Viewpager是低版本的,那么是没问题的.
     但是如果你编译时用是高版本的 Api 5.0 以后吧.上面这个自定义就会失效,
     你会发现,会有细微的滑动.还是能滑动的
     */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
//        Log.e("哈哈", super.onInterceptTouchEvent(event) + "");

        //这样写 禁止滑动后，滑动操作不会触发点击事件，但是滑动操作会挪动图片
//        return  super.onInterceptTouchEvent(event);

        //这样写 禁止滑动后，滑动操作会触发点击事件，滑动操作不会挪动图片
//        return isCanScroll && super.onInterceptTouchEvent(event);

        //这样写 禁止滑动后，滑动操作会触发点击事件，滑动操作不会挪动图片
//        if (isCanScroll ) {
//            return super.onInterceptTouchEvent(event);
//        } else {
//            return false;
//        }

        /**
         * 其实，按照昨天你最后发给我的那样写，我发现已经是实现无法滑动了的。。。我也想不明白你最后为什么说一样可以滑动，
         * 这。。。。然后就是依然是可以点击，我猜想应该是因为onInterceptTouchEvent这个方法返回false，
         * 所以事件往下传递到imageview那里了，所以我修改了一下，设置不可滑动的时候，如果我们进行了滑动，就返回true，
         * 然后事件就不会往下传了，直接传递到自己的onTouchEvent那里进行处理，而我们的onTouchEvent什么都没错，因此，ok
         */
        if (isCanScroll) {
            return super.onInterceptTouchEvent(event);
        } else {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downX = event.getX();
                    downY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (event.getX() - downX >= 30 || event.getY() - downY >= 30) {
                        // 滑动了,拦截事件，不往下传递了。
                        return true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    break;
            }
            return false;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return isCanScroll && super.onTouchEvent(event);

//        if (isCanScroll) {
//            return super.onTouchEvent(event);
//        } else {
//            return false;
//        }

    }



}
