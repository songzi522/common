package com.gs.common.activity.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gs.common.R;

import java.util.ArrayList;
import java.util.Collection;

public class Test1Activity extends AppCompatActivity {

    private static final String TAG = "Test1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        functionCollection();
    }

    private void functionCollection() {
        Collection collection = new ArrayList();
        show(collection);
    }


    public static void show(Collection collection) {
        //1、添加元素，add
        collection.add("aaa");
        collection.add("bbb");
        collection.add("ccc");

        Log.e(TAG, collection.toString());
    }

    /**
     * 截取字符串
     */
    private void functionSubstring() {
        String string1 = "http://phdzyapi.phmd247.com/";
        String string2 = string1.substring(0, string1.length() - 1);

        Log.e(TAG, string2);
    }

    /**
     * 实现一个字符串倒序
     */
    private void functionReverse() {
        String src = "ABCDEF ";
        String dst = new StringBuffer(src).reverse().toString();

        Log.e(TAG, "dst: " + dst);
    }


}
