package com.gs.common.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gs.common.R;

public class Fragment01 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //引用创建好的xml布局
        return inflater.inflate(R.layout.fragment01, container, false);

    }



}
