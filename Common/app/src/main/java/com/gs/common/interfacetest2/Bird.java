package com.gs.common.interfacetest2;

import android.util.Log;


/**
 * 接口使用1： 解决“多重继承”的问题
 * Java语言本身是不支持类的多重继承(多重继承是指一个类从多个类继承而来，即一个类拥有多个超类)的，
 * 但一个类却可以实现多个接口。这样，我们可以将一些抽象方法定义在接口中，间接地达到多重继承的目的
 */
public class Bird implements MyInterface1, MyInterface2 {

    private static final String TAG = "Bird";

    @Override
    public void fly() {
        Log.i(TAG, "I can fly");
    }

    @Override
    public void walk() {
        Log.i(TAG, "I can walk");
    }

}